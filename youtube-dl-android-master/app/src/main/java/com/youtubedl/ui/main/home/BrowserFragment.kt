package com.youtubedl.ui.main.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.support.design.widget.BottomSheetDialog
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import com.youtubedl.OpenForTesting
import com.youtubedl.databinding.FragmentBrowserBinding
import com.youtubedl.ui.component.adapter.SuggestionAdapter
import com.youtubedl.ui.component.adapter.TopPageAdapter
import com.youtubedl.ui.component.dialog.DownloadVideoListener
import com.youtubedl.ui.component.dialog.showDownloadVideoDialog
import com.youtubedl.ui.main.base.BaseFragment
import com.youtubedl.ui.main.player.VideoPlayerActivity
import com.youtubedl.ui.main.player.VideoPlayerFragment.Companion.VIDEO_NAME
import com.youtubedl.ui.main.player.VideoPlayerFragment.Companion.VIDEO_URL
import com.youtubedl.util.AppUtil
import javax.inject.Inject

/**
 * Created by cuongpm on 12/7/18.
 */

@OpenForTesting
class BrowserFragment : BaseFragment() {

    companion object {
        fun newInstance() = BrowserFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var mainActivity: MainActivity

    @Inject
    lateinit var appUtil: AppUtil

    @VisibleForTesting
    internal lateinit var dataBinding: FragmentBrowserBinding

    private lateinit var browserViewModel: BrowserViewModel

    private lateinit var mainViewModel: MainViewModel

    private lateinit var topPageAdapter: TopPageAdapter

    private lateinit var suggestionAdapter: SuggestionAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mainViewModel = mainActivity.mainViewModel
        browserViewModel = ViewModelProviders.of(this, viewModelFactory).get(BrowserViewModel::class.java)
        topPageAdapter = TopPageAdapter(ArrayList(0), browserViewModel)
        suggestionAdapter = SuggestionAdapter(context, ArrayList(0), browserViewModel)

        dataBinding = FragmentBrowserBinding.inflate(inflater, container, false).apply {
            this.viewModel = browserViewModel
            this.webChromeClient = browserWebChromeClient
            this.webViewClient = browserWebViewClient
            this.rvTopPages.layoutManager = LinearLayoutManager(context)
            this.rvTopPages.adapter = topPageAdapter
            this.etSearch.setAdapter(suggestionAdapter)
            this.etSearch.setOnKeyListener(onKeyPressEnterListener)
            this.etSearch.addTextChangedListener(onInputChangeListener)
        }

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        browserViewModel.start()
        handlePressBackBtnEvent()
        handleChangeFocusEvent()
        handleDownloadVideoEvent()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        browserViewModel.stop()
    }

    private fun onBackPressed() {
        when {
            dataBinding.webview.canGoBack() -> dataBinding.webview.goBack()
            browserViewModel.isShowPage.get() -> browserViewModel.isShowPage.set(false)
            else -> activity?.finish()
        }
    }

    private fun handlePressBackBtnEvent() {
        mainViewModel.pressBackBtnEvent.observe(this@BrowserFragment, Observer {
            activity?.runOnUiThread { onBackPressed() }
        })
    }

    private fun handleChangeFocusEvent() {
        browserViewModel.changeFocusEvent.observe(this@BrowserFragment, Observer { isFocus ->
            isFocus?.let {
                if (it) appUtil.showSoftKeyboard(dataBinding.etSearch) else appUtil.hideSoftKeyboard(
                    dataBinding.etSearch
                )
            }
        })
    }

    private fun handleDownloadVideoEvent() {
        browserViewModel.showDownloadDialogEvent.observe(this@BrowserFragment, Observer { videoInfo ->
            activity?.runOnUiThread {
                showDownloadVideoDialog(requireActivity(), object : DownloadVideoListener {
                    override fun onPreviewVideo(dialog: BottomSheetDialog) {
                        dialog.dismiss()
                        videoInfo?.let {
                            val intent = Intent(context, VideoPlayerActivity::class.java)
                            intent.putExtra(VIDEO_URL, it.downloadUrl)
                            intent.putExtra(VIDEO_NAME, it.name)
                            startActivity(intent)
                        }
                    }

                    override fun onDownloadVideo(dialog: BottomSheetDialog) {
                        mainViewModel.downloadVideoEvent.value = videoInfo
                        dialog.dismiss()
                    }

                    override fun onCancel(dialog: BottomSheetDialog) {
                        dialog.dismiss()
                    }
                })
            }
        })
    }

    private val onInputChangeListener = object : TextWatcher {
        override fun afterTextChanged(s: Editable) {
            val input = s.toString()
            browserViewModel.textInput.set(input)
            browserViewModel.showSuggestions()
            browserViewModel.publishSubject.onNext(input)
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }
    }

    private val onKeyPressEnterListener = View.OnKeyListener { v, keyCode, _ ->
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            browserViewModel.loadPage((v as EditText).text.toString())
            return@OnKeyListener true
        }
        return@OnKeyListener false
    }

    private val browserWebChromeClient = object : WebChromeClient() {
        override fun onProgressChanged(view: WebView, newProgress: Int) {
            browserViewModel.progress.set(newProgress)
            super.onProgressChanged(view, newProgress)
        }
    }

    private val browserWebViewClient = object : WebViewClient() {
        override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
            browserViewModel.startPage(view.url)
            super.onPageStarted(view, url, favicon)
        }


        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            browserViewModel.textInput.set(url)
            browserViewModel.pageUrl.set(url)
            return super.shouldOverrideUrlLoading(view, url)
        }

        override fun onLoadResource(view: WebView, url: String) {
            browserViewModel.loadResource(view.url)
            super.onLoadResource(view, url)
        }

        override fun onPageFinished(view: WebView, url: String) {
            browserViewModel.finishPage(view.url)
            super.onPageFinished(view, url)
        }
    }
}