package com.module.mine.data

import com.base.base.api.ApiUrl
import com.base.base.entity.remote.UserSquareShare
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toApiResponse

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-10 9:45 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
object ShareRepository {

    /**
     * 个人分享列表
     * @param page Int 页码
     * @return UserSquareShare
     */
    suspend fun getShareList(page: Int): UserSquareShare {
        return RxHttp.get(ApiUrl.Share.ShareList, page)
            .toApiResponse<UserSquareShare>()
            .await()
    }

    /**
     * 新增分享
     * @param title String 标题
     * @param link String 链接
     * @return Any
     */
    suspend fun addShare(title: String, link: String): Any {
        return RxHttp.postForm(ApiUrl.Share.ShareAdd, title, link)
            .add("title", title)
            .add("link", link)
            .toApiResponse<Any>()
            .await()
    }

    /**
     * 删除分享
     * @param id Int 文章id
     * @return Any
     */
    suspend fun deleteShare(id: Int): Any {
        return RxHttp.postForm(ApiUrl.Share.ShareDelete, id)
            .toApiResponse<Any>()
            .await()
    }
}