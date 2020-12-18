package com.base.base.http

/**
 * 网络请求回调
 */
data class ApiResponse<T>(val code: Int, val data: T, val msg: String) {
    fun isSuccess() = code == ApiCode.Success
}

/**
 * 请求失败异常
 */
class ApiException(var code: Int, override var message: String) : Exception(message)