package com.youtubedl.ui.component.binding

import android.databinding.BindingAdapter
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient

/**
 * Created by cuongpm on 12/19/18.
 */

object WebViewBinding {

    @BindingAdapter("app:loadUrl")
    @JvmStatic
    fun WebView.loadUrl(url: String?) {
        url?.let { if (url.isNotEmpty()) loadUrl(it) }
    }

    @BindingAdapter("app:javaScriptEnabled")
    @JvmStatic
    fun WebView.javaScriptEnabled(isEnabled: Boolean?) {
        isEnabled?.let { settings.javaScriptEnabled = it }
    }

    @BindingAdapter("app:addJavascriptInterface")
    @JvmStatic
    fun WebView.addJavascriptInterface(name: String?) {
        name?.let { addJavascriptInterface(context, it) }
    }

    @BindingAdapter("app:webViewClient")
    @JvmStatic
    fun WebView.webViewClient(webViewClient: WebViewClient?) {
        webViewClient?.let { this.webViewClient = it }
    }

    @BindingAdapter("app:webChromeClient")
    @JvmStatic
    fun WebView.webChromeClient(webChromeClient: WebChromeClient?) {
        webChromeClient?.let { this.webChromeClient = it }
    }
}