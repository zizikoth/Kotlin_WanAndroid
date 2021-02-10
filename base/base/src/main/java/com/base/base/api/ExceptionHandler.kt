package com.base.base.api

import android.net.ParseException
import com.blankj.utilcode.util.LogUtils
import com.bumptech.glide.load.HttpException
import com.google.gson.JsonParseException
import org.json.JSONException
import java.net.ConnectException
import java.net.SocketException
import java.net.UnknownHostException

/**
 * title:ExceptionHandler
 * describe:网络请求失败处理
 *
 * @author memo
 * @date 2020-12-18 23:40
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
object ExceptionHandler {

    fun handleException(exception: Throwable): ApiException {
        //错误日志打印
        LogUtils.eTag("HTTP ERROR", exception.toString())
        return when (exception) {
            // 服务器返回的错误
            is ApiException -> exception
            // 解析错误
            is JsonParseException,
            is JSONException,
            is ParseException -> ApiException(ApiCode.ServerError, "数据解析失败")
            // 连接错误
            is HttpException,
            is ConnectException,
            is SocketException -> ApiException(ApiCode.ServerError, "无法连接服务器")
            // 网络错误
            is UnknownHostException -> ApiException(ApiCode.NetError, "网络异常")
            // 未知错误
            else -> ApiException(ApiCode.ServerError, "服务器异常")
        }
    }
}