package com.base.base.api

/**
 * 网络请求回调
 */
data class ApiResponse<T>(var data: T?, val errorCode: Int = 0, val errorMsg: String = "") {
    fun isSuccess() = errorCode == ApiCode.Success
    fun isNeedLogin() = errorCode == ApiCode.UnLogin
}

