package com.youtubedl.ui.main.home

import android.databinding.*
import android.support.annotation.VisibleForTesting
import android.util.Patterns
import com.youtubedl.OpenForTesting
import com.youtubedl.data.local.model.Suggestion
import com.youtubedl.data.local.room.entity.PageInfo
import com.youtubedl.data.local.room.entity.VideoInfo
import com.youtubedl.data.repository.ConfigRepository
import com.youtubedl.data.repository.TopPagesRepository
import com.youtubedl.data.repository.VideoRepository
import com.youtubedl.ui.main.base.BaseViewModel
import com.youtubedl.util.ScriptUtil
import com.youtubedl.util.SingleLiveEvent
import com.youtubedl.util.scheduler.BaseSchedulers
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern
import javax.inject.Inject

/**
 * Created by cuongpm on 12/7/18.
 */

@OpenForTesting
class BrowserViewModel @Inject constructor(
    private val topPagesRepository: TopPagesRepository,
    private val configRepository: ConfigRepository,
    private val videoRepository: VideoRepository,
    private val baseSchedulers: BaseSchedulers
) : BaseViewModel() {

    companion object {
        const val SEARCH_URL = "https://www.google.com/search?q=%s"
    }

    @VisibleForTesting
    internal lateinit var compositeDisposable: CompositeDisposable

    lateinit var publishSubject: PublishSubject<String>

    val textInput = ObservableField<String>("")
    val isFocus = ObservableBoolean(false)
    val isLoadingVideoInfo = ObservableBoolean(false)

    val pageUrl = ObservableField<String>("")
    val isShowPage = ObservableBoolean(false)
    val javaScriptEnabled = ObservableBoolean(true)
    val javaScripInterface = ObservableField<String>("browser")

    val isShowProgress = ObservableBoolean(false)
    val progress = ObservableInt(0)

    val isShowFabBtn = ObservableBoolean(false)
    val isExpandedAppbar = ObservableBoolean(true)

    val listPages: ObservableList<PageInfo> = ObservableArrayList()
    val listSuggestions: ObservableList<Suggestion> = ObservableArrayList()

    val changeFocusEvent = SingleLiveEvent<Boolean>()
    val showDownloadDialogEvent = SingleLiveEvent<VideoInfo>()

    override fun start() {
        compositeDisposable = CompositeDisposable()
        publishSubject = PublishSubject.create()
        getTopPages()
    }

    override fun stop() {
        compositeDisposable.clear()
    }

    fun loadPage(input: String, pattern: Pattern = Patterns.WEB_URL) {
        if (input.isNotEmpty()) {
            isShowPage.set(true)
            changeFocus(false)

            if (input.startsWith("http://") || input.startsWith("https://")) {
                pageUrl.set(input)
            } else if (pattern.matcher(input).matches()) {
                pageUrl.set("http://$input")
                textInput.set("http://$input")
            } else {
                pageUrl.set(String.format(SEARCH_URL, input))
                textInput.set(String.format(SEARCH_URL, input))
            }
        }
    }

    fun changeFocus(isFocus: Boolean) {
        this.isFocus.set(isFocus)
        changeFocusEvent.value = isFocus
    }

    fun startPage(url: String) {
        textInput.set(url)
        isShowPage.set(true)
        isShowProgress.set(true)
        isExpandedAppbar.set(true)
        verifyLinkStatus(url)
    }

    fun loadResource(url: String) {
        textInput.set(url)
        verifyLinkStatus(url)
        if (url.contains("facebook.com")) {
            pageUrl.set(ScriptUtil.FACEBOOK_SCRIPT)
        }
    }

    fun finishPage(url: String) {
        textInput.set(url)
        isShowProgress.set(false)
        verifyLinkStatus(url)
    }

    @VisibleForTesting
    internal fun verifyLinkStatus(url: String) {
        configRepository.getSupportedPages()
            .flatMap { pages ->
                Flowable.fromIterable(pages)
                    .filter { page ->
                        url.matches(page.pattern.toRegex()) || url.contains(page.pattern)
                    }.toList().toFlowable()
            }
            .subscribeOn(baseSchedulers.io)
            .observeOn(baseSchedulers.mainThread)
            .firstOrError()
            .doOnSubscribe { compositeDisposable.add(it) }
            .subscribe({ pages ->
                isShowFabBtn.set(pages.isNotEmpty())
            }, { error ->
                error.printStackTrace()
                isShowFabBtn.set(false)
            })
    }

    fun showSuggestions() {
        getListSuggestions()
            .subscribeOn(baseSchedulers.io)
            .observeOn(baseSchedulers.mainThread)
            .firstOrError()
            .doOnSubscribe { compositeDisposable.add(it) }
            .subscribe({ list ->
                with(listSuggestions) {
                    clear()
                    addAll(list)
                }
            }, { error ->
                error.printStackTrace()
            })
    }

    fun getVideoInfo() {
        textInput.get()?.let { url ->
            videoRepository.getVideoInfo(url)
                .doOnSubscribe { isLoadingVideoInfo.set(true) }
                .doAfterTerminate { isLoadingVideoInfo.set(false) }
                .subscribeOn(baseSchedulers.io)
                .observeOn(baseSchedulers.mainThread)
                .firstOrError()
                .doOnSubscribe { compositeDisposable.add(it) }
                .subscribe({ videoInfo ->
                    showDownloadDialogEvent.value = videoInfo
                }, { error ->
                    error.printStackTrace()
                })
        }
    }

    private fun getListSuggestions(): Flowable<List<Suggestion>> {
        return Flowable.combineLatest(
            publishSubject.debounce(300, TimeUnit.MILLISECONDS).toFlowable(BackpressureStrategy.LATEST),
            configRepository.getSupportedPages(),
            BiFunction { input, supportedPages ->
                val listSuggestions = mutableListOf<Suggestion>()
                supportedPages.filter { page -> page.name.contains(input) }.map {
                    listSuggestions.add(Suggestion(content = it.name))
                }
                listSuggestions
            }
        )
    }

    private fun getTopPages() {
        topPagesRepository.getTopPages()
            .subscribeOn(baseSchedulers.io)
            .observeOn(baseSchedulers.mainThread)
            .firstOrError()
            .doOnSubscribe { compositeDisposable.add(it) }
            .subscribe({ list ->
                with(listPages) {
                    clear()
                    addAll(list)
                }
            }, { error ->
                error.printStackTrace()
            })
    }

}