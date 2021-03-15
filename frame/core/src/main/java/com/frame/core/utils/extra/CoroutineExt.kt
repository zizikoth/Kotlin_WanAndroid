package com.frame.core.utils.extra

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.random.Random

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

// ------------------------------- Callback => Coroutine 例子 -------------------------------//
interface CoroutineCallback {
    fun onSuccess(num: Int)
    fun onFailure(e: Exception)
}

object CoroutineDemoUtils {
    fun getDemoData(callback: CoroutineCallback) {
        val num = Random.nextInt(0, 9)
        if (num >= 5) callback.onSuccess(num)
        else callback.onFailure(Exception("number > 5"))
    }
}

suspend fun getCoroutineDemoData(): Int =
    suspendCancellableCoroutine {
        CoroutineDemoUtils.getDemoData(object : CoroutineCallback {
            override fun onSuccess(num: Int) {
                it.resume(num)
            }

            override fun onFailure(e: Exception) {
                it.resumeWithException(e)
            }
        })
    }
