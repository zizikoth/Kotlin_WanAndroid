package com.frame.core.core

import android.app.Application
import com.bytedance.boost_multidex.BoostMultiDexApplication
import com.frame.core.utils.SystemLeakIgnore

/**
 * title:CoreApp
 * describe:
 *
 * @author memo
 * @date 2020-12-18 10:30 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
open class CoreApp : BoostMultiDexApplication() {

    companion object {
        lateinit var app: Application
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        SystemLeakIgnore.ignoreLeak()
    }
}