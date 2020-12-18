package com.base.base.http

/**
 * 网络请求回调
 */
data class ApiResponse<T>(val data: T, val errorCode: Int = 0, val errorMsg: String = "") {
    fun isSuccess() = errorCode == ApiCode.Success
}

/**
 * 请求失败异常
 */
class ApiException(var code: Int, override var message: String) : Exception(message)