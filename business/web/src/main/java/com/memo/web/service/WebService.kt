package com.memo.web.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.webkit.WebView
import com.blankj.utilcode.util.LogUtils

/**
 * title: 提前初始化减少WebView创建时间
 * describe:
 *
 * @author memo
 * @date 2020-12-22 10:41 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class WebService : Service() {

    override fun onCreate() {
        super.onCreate()
        LogUtils.iTag("WebService", "提前创建WebView","请使用WebView的Activity添加 android:process=\":web\" ")
        WebView(this.applicationContext)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}