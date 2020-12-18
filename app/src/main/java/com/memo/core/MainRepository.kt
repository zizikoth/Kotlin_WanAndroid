package com.memo.core

import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toApiResponse

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-12-19 2:01 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
object MainRepository {

    @JvmStatic
    suspend fun getBanner(): ArrayList<HomeBanner> {
        return RxHttp.get("banner/json")
            .toApiResponse<ArrayList<HomeBanner>>()
            .await()
    }
}