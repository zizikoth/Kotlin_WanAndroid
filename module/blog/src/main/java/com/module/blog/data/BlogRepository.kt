package com.module.blog.data

import com.base.base.api.ApiUrl
import com.base.base.entity.remote.ArticleList
import com.base.base.entity.remote.ArticleTree
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toApiResponse

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-01 11:12 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
object BlogRepository {

    /**
     * 获取博客名称
     */
    suspend fun getBlogTree(): ArrayList<ArticleTree> {
        return RxHttp.get(ApiUrl.Blog.BlogTree)
            .toApiResponse<ArrayList<ArticleTree>>()
            .await()
    }

    /**
     * 获取博客文章列表
     * @param page Int 页码
     * @param cid Int id
     * @param word String 关键字
     * @return ArticleList
     */
    suspend fun getBlogArticles(page: Int, cid: Int, word: String): ArticleList {
        return RxHttp.get(ApiUrl.Blog.BlogArticles, cid, page)
            .add("k", word)
            .toApiResponse<ArticleList>()
            .await()
    }


}