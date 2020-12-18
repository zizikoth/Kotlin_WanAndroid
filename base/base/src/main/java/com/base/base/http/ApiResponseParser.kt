package com.base.base.http

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

    override fun onParse(response: Response): T {
        val type: Type = ParameterizedTypeImpl[ApiResponse::class.java, mType]
        val data: ApiResponse<T> = response.convert(type)
        if (!data.isSuccess()) {
            throw ApiException(data.code, data.msg)
        }
        return data.data
    }
}