package com.memo.web.utils

import android.content.Intent
import com.memo.web.service.WebService
import com.frame.core.core.CoreApp

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
        CoreApp.app.startService(Intent(CoreApp.app, WebService::class.java))
    }

}