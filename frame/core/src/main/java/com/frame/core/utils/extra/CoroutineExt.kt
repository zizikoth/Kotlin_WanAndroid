package com.frame.core.utils.extra

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume

/**
 * title:使用协程进行子线程和追线程的切换
 * describe:
 *
 * @author memo
 * @date 2020-04-27 19:04
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
fun <T> LifecycleOwner.doInBackground(
    doInBackground: () -> T,
    onSuccess: (result: T) -> Unit,
    onError: (e: Throwable) -> Unit
) {
    this.lifecycleScope.launch(Dispatchers.Main) {
        try {
            onSuccess(withContext(Dispatchers.IO) { doInBackground() })
        } catch (e: Throwable) {
            onError(e)
        }
    }
}

fun <T> LifecycleOwner.doInBackground(doInBackground: () -> T, onSuccess: (result: T) -> Unit) =
    this.doInBackground(doInBackground, onSuccess, {})

fun <T> LifecycleOwner.doInBackground(doInBackground: () -> T) =
    this.doInBackground(doInBackground, {}, {})

interface CoroutineCallback<T> {
    fun onSuccess(result: T)
}

private suspend fun <T> awaitCallback(block: (CoroutineCallback<T>) -> Unit): T {
    return suspendCancellableCoroutine {
        block(object : CoroutineCallback<T> {
            override fun onSuccess(result: T) {
                it.resume(result)
            }
        })
    }
}