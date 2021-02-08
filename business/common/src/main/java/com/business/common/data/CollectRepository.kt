package com.business.common.data

import com.base.base.api.ApiUrl
import rxhttp.tryAwait
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toApiResponse

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-07 10:53 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
object CollectRepository {

    /**
     * 收藏
     * @param id Int
     * @return Any?
     */
    suspend fun collect(id: Int): Any? {
        return RxHttp.postForm(ApiUrl.Collect.Collect, id)
            .toApiResponse<Any>()
            .tryAwait()
    }

    /**
     * 取消收藏 - 文章网页
     * @param id Int
     * @return Any?
     */
    suspend fun unCollectInArticle(id: Int): Any? {
        return RxHttp.postForm(ApiUrl.Collect.UnCollectInArticle, id)
            .toApiResponse<Any>()
            .tryAwait()

    }

    /**
     * 取消收藏
     * @param id Int 文章id
     * @param originId Int 接口返回
     * @return Any
     */
    suspend fun unCollectInList(id: Int, originId: Int): Any? {
        return RxHttp.postForm(ApiUrl.Collect.UnCollectInList, id)
            .add("originId", originId)
            .toApiResponse<Any>()
            .tryAwait()
    }

}