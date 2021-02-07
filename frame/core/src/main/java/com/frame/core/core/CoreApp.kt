package com.frame.core.core

import android.app.Application
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
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
open class CoreApp : BoostMultiDexApplication(), ViewModelStoreOwner {

    companion object {
        lateinit var app: Application
    }

    private lateinit var mAppViewStore: ViewModelStore

    override fun onCreate() {
        super.onCreate()
        app = this
        mAppViewStore = ViewModelStore()
        SystemLeakIgnore.ignoreLeak()
    }

    override fun getViewModelStore(): ViewModelStore {
        return mAppViewStore
    }
}