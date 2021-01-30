package com.base.base.api

/**
 * 请求失败异常
 */
class ApiException(var code: Int, override var message: String) : Exception(message)