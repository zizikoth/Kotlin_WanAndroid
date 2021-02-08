package com.base.web.utils.web

import android.graphics.Bitmap
import android.net.Uri
import android.net.http.SslError
import android.os.Build
import android.webkit.SslErrorHandler
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import androidx.annotation.RequiresApi
import com.blankj.utilcode.util.LogUtils
import com.just.agentweb.WebViewClient

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-08 10:32 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class BlockWebClient() : WebViewClient() {

    private var blockIntent = true
    private var blockAd = true

    // App跳转
    private val intentScheme = arrayListOf(
        "mailto://",            //邮件
        "tell://",              //电话
        "bilibili://",          //BiliBili
        "weixin://",            //微信
        "alipays://",           //支付宝
        "dianping://",          //大众点评
        "tbopen://",            //淘宝
        "openapp.jdmobile://",  //京东
        "tmast://",             //天猫
        "pinduoduo://"          //拼多多
    )

    // 拦截的网址
    private val blackHostList = arrayListOf(
        "www.taobao.com",
        "www.jd.com",
        "yun.tuisnake.com",
        "yun.lvehaisen.com",
        "yun.tuitiger.com"
    )

    fun shouldIntent2App(block: Boolean): BlockWebClient {
        blockIntent = block
        return this
    }

    fun shouldBlockAd(block: Boolean): BlockWebClient {
        blockAd = block
        return this
    }

    private fun isBlackHost(host: String): Boolean {
        return blackHostList.contains(host)
    }

    private fun isIntentHost(url: String?): Boolean {
        intentScheme.forEach {
            if (url?.startsWith(it) == true) {
                return true
            }
        }
        return false
    }

    private fun shouldInterceptRequest(uri: Uri?): Boolean {
        val host = uri?.host.orEmpty()
        return (blockAd && isBlackHost(host)) || (blockIntent && isIntentHost(uri.toString()))
    }

    private fun shouldOverrideUrlLoading(uri: Uri?): Boolean {
        val host = uri?.host.orEmpty()
        return (blockAd && isBlackHost(host)) || (blockIntent && isIntentHost(uri.toString()))
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun shouldInterceptRequest(view: WebView?, request: WebResourceRequest?): WebResourceResponse? {
        if (shouldInterceptRequest(request?.url)) {
            return WebResourceResponse(null, null, null)
        }
        return super.shouldInterceptRequest(view, request)
    }

    override fun shouldInterceptRequest(view: WebView?, url: String?): WebResourceResponse? {
        if (shouldInterceptRequest(Uri.parse(url))) {
            return WebResourceResponse(null, null, null)
        }
        return super.shouldInterceptRequest(view, url)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        return shouldOverrideUrlLoading(request?.url)
    }

    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        return shouldOverrideUrlLoading(Uri.parse(url))
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        LogUtils.iTag("BlockWebClient", url)
        super.onPageStarted(view, url, favicon)
    }

    /**
     * 屏蔽异常
     * @param view WebView
     * @param handler SslErrorHandler
     * @param error SslError
     */
    override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
        // super.onReceivedSslError(view, handler, error)
        handler?.proceed()
    }

}