package com.frame.core.utils.extra

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-01-30 4:04 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
fun <T> LifecycleOwner.observe(liveData: LiveData<T>, observer: (t: T) -> Unit) {
    liveData.observe(this, observer)
}
