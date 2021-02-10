package com.base.base.api

import com.base.base.manager.UserManager
import com.blankj.utilcode.util.LogUtils
import okhttp3.Response
import rxhttp.wrapper.annotation.Parser
import rxhttp.wrapper.entity.ParameterizedTypeImpl
import rxhttp.wrapper.parse.AbstractParser
import rxhttp.wrapper.utils.convert
import java.lang.reflect.Type

/**
 * title:网络请求解析数据
 * describe:
 *
 * @author memo
 * @date 2020-12-18 11:56 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
@Parser(name = "ApiResponse")
open class ApiResponseParser<T> : AbstractParser<T> {

    protected constructor() : super()
    constructor(type: Type) : super(type)

    @Throws(Exception::class)
    override fun onParse(response: Response): T {
        val type: Type = ParameterizedTypeImpl[ApiResponse::class.java, mType]
        val data: ApiResponse<T> = response.convert(type)
        if (!data.isSuccess()) {
            // 无论是那个接口如果需要登陆 那么清除cookie
            if (data.isNeedLogin()) {
                UserManager.loginOut()
            }
            throw ApiException(data.errorCode, data.errorMsg)
        } else if (data.data == null) {
            data.data = (Any() as T)
        }
        return data.data!!
    }
}