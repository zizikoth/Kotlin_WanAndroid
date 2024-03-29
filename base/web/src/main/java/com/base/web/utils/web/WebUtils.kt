package com.base.web.utils.web

import android.content.Intent
import com.base.web.service.WebService
import com.frame.core.core.CoreApp
import com.frame.core.utils.extra.doTryCatch
import com.queue.library.GlobalQueue

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-12-22 10:47 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
object WebUtils {

    /**
     * 请在合适的地方进行初始化
     */
    fun preInit() {
        GlobalQueue.getMainQueue().postRunnableInIdleRunning {
            doTryCatch { CoreApp.app.startService(Intent(CoreApp.app, WebService::class.java)) }
        }
    }

}