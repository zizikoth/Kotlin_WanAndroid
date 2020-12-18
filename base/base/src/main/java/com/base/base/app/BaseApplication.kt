package com.base.base.app

import com.base.base.manager.InitManager
import com.frame.core.core.CoreApp

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
class BaseApplication: CoreApp() {

    override fun onCreate() {
        super.onCreate()
        InitManager.initInApp(this)
        InitManager.initLater()
    }
}