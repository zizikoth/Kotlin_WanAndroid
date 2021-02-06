package com.frame.core.utils.extra

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-06 6:22 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
fun <T> doTryCatch(doTry: () -> T, doException: (Exception) -> T, doFinally: () -> T): T {
    return try {
        doTry.invoke()
    } catch (e: Exception) {
        doException.invoke(e)
    } finally {
        doFinally.invoke()
    }
}

fun doTryCatch(doTry: () -> Unit) {
    doTryCatch({ doTry.invoke() }, {}, {})
}