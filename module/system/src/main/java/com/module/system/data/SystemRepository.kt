package com.module.system.data

import com.base.base.api.ApiUrl
import com.base.base.entity.remote.ArticleList
import com.base.base.entity.remote.SystemTree
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toApiResponse

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-01 5:37 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
object SystemRepository {

    /**
     * 获取体系列表
     */
    suspend fun getSystemTree(): ArrayList<SystemTree> {
        return RxHttp.get(ApiUrl.System.SystemTree)
            .toApiResponse<ArrayList<SystemTree>>()
            .await()
    }

    /**
     * 获取体系文章
     * @param page Int 页码
     * @param cid Int id
     * @return ArticleList
     */
    suspend fun getSystemArticles(page: Int, cid: Int): ArticleList {
        return RxHttp.get(ApiUrl.System.SystemArticles, page)
            .add("cid", cid)
            .toApiResponse<ArticleList>()
            .await()
    }

    /**
     * 获取导航列表
     * @return ArrayList<SystemTree>
     */
    suspend fun getNaviTree(): ArrayList<SystemTree> {
        return RxHttp.get(ApiUrl.System.NaviTree)
            .toApiResponse<ArrayList<SystemTree>>()
            .await()
    }
}