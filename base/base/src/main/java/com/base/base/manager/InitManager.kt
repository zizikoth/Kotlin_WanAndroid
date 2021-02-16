package com.base.base.manager

import android.app.Application
import com.base.base.config.AppConfig
import com.base.base.ui.status.LoadCallback
import com.base.base.ui.status.NetErrorCallback
import com.base.base.ui.status.ServerErrorCallback
import com.base.web.utils.web.WebUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ProcessUtils
import com.blankj.utilcode.util.Utils
import com.didichuxing.doraemonkit.DoraemonKit
import com.frame.core.utils.GsonHelper
import com.frame.core.utils.OOMHelper
import com.frame.core.utils.extra.dimen
import com.kongzue.dialog.util.DialogSettings
import com.load.status.core.LoadStatus
import com.scwang.smart.refresh.footer.BallPulseFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import okhttp3.OkHttpClient
import rxhttp.wrapper.converter.GsonConverter
import rxhttp.wrapper.cookie.CookieStore
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.ssl.HttpsUtils
import java.io.File
import android.graphics.Color
import androidx.core.content.ContextCompat
import com.alibaba.android.arouter.launcher.ARouter
import com.base.base.BuildConfig
import com.base.base.R
import java.util.concurrent.TimeUnit


/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-12-18 10:38 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
object InitManager {

    /**
     * 在Application进行初始化
     */
    fun initInApp(app: Application) {
        if (ProcessUtils.isMainProcess()) {
            // DoKit
            DoraemonKit.install(app)

            // AndroidCodeUtils
            Utils.init(app)
            LogUtils.getConfig().setLogSwitch(AppConfig.isOpenLog)

            // ARouter
            if (BuildConfig.DEBUG) {
                ARouter.openLog()
                ARouter.openDebug()
            }
            ARouter.init(app)

            // RxHttp
            val sslFactory = HttpsUtils.getSslSocketFactory()
            val client: OkHttpClient = OkHttpClient.Builder()
                .cookieJar(CookieStore(File(app.externalCacheDir, "HttpCookie")))
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .sslSocketFactory(sslFactory.sSLSocketFactory, sslFactory.trustManager)
                .hostnameVerifier { _, _ -> true }
                .build()
            RxHttp.init(client)
            RxHttp.setDebug(AppConfig.isOpenLog, true)
            RxHttp.setConverter(GsonConverter.create(GsonHelper.getGson()))

        }
    }

    /**
     * 延迟初始化
     */
    fun initLater() {
        // oom
        OOMHelper.startMonitorLowMemory()
        // WebView
        //WebUtils.preInit()
        // Dialog
        DialogSettings.style = DialogSettings.STYLE.STYLE_MIUI
        DialogSettings.backgroundColor = Color.WHITE
        DialogSettings.DEBUGMODE = BuildConfig.DEBUG
        DialogSettings.init()

        // LoadStatus
        LoadStatus.beginBuilder()
            .addCallback(LoadCallback())
            .addCallback(ServerErrorCallback())
            .addCallback(NetErrorCallback())
            .setDefaultCallback(LoadCallback::class.java)
            .commit()

        // 初始化刷新框架
        SmartRefreshLayout.setDefaultRefreshInitializer { _, refreshLayout ->
            refreshLayout
                .setEnableAutoLoadMore(false)
                .setEnableOverScrollBounce(true)
                .setEnableOverScrollDrag(true)
                .setEnableLoadMoreWhenContentNotFull(true)
        }
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, _ ->
            ClassicsHeader(context)
        }
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, _ ->
            val footer = BallPulseFooter(context)
                .setNormalColor(ContextCompat.getColor(context, R.color.color_666666))
                .setAnimatingColor(ContextCompat.getColor(context, R.color.color_666666))
            footer.minimumHeight = dimen(R.dimen.dp50).toInt()
            footer.minimumWidth = dimen(R.dimen.dp50).toInt()
            footer
        }

    }
}