package com.frame.core.utils

import leakcanary.LeakCanary
import shark.AndroidReferenceMatchers

/**
 * title:忽略由于系统的内存泄漏
 * describe:
 *
 * @author memo
 * @date 2020-12-24 3:33 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
object SystemLeakIgnore {

    private val ignoreList = listOf(
        AndroidReferenceMatchers.staticFieldLeak("android.view.HwNsdImpl", "sInstance"),
        AndroidReferenceMatchers.staticFieldLeak("com.android.internal.policy.HwPhoneWindow", "mContext"),
    )

    fun ignoreLeak() {
        LeakCanary.config = LeakCanary.config.copy(
            referenceMatchers = AndroidReferenceMatchers.appDefaults.plus(ignoreList)
        )
    }
}