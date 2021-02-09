package com.module.mine.data

import com.base.base.api.ApiUrl
import com.base.base.entity.remote.Article
import com.base.base.entity.remote.PageList
import com.base.base.entity.remote.UserSquareShare
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toApiResponse

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-10 2:00 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
object SquareRepository {

    /**
     * 获取广场列表
     * @param page Int 页码
     * @return PageList<Article>
     */
    suspend fun getSquareList(page: Int): PageList<Article> {
        return RxHttp.get(ApiUrl.Square.SquareList, page)
            .toApiResponse<PageList<Article>>()
            .await()
    }

    /**
     * 获取用户的广场分享信息
     * @param userId Int 用户id
     * @param page Int 页码
     * @return UserSquareShare
     */
    suspend fun getSquareUserShareList(userId: Int, page: Int): UserSquareShare {
        return RxHttp.get(ApiUrl.Square.SquareUserShareList, userId, page)
            .toApiResponse<UserSquareShare>()
            .await()
    }
}