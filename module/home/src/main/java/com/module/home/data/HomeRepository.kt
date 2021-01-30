package com.module.home.data

import com.base.base.api.ApiUrl
import com.base.base.entity.remote.Article
import com.base.base.entity.remote.ArticleList
import com.base.base.entity.remote.HomeBanner
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toApiResponse

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-01-30 2:22 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
object HomeRepository {

    /**
     * 获取首页Banner
     * @return ArrayList<HomeBanner>
     */
    suspend fun getBanner(): ArrayList<HomeBanner> {
        return RxHttp.get(ApiUrl.Home.Banner)
            .toApiResponse<ArrayList<HomeBanner>>()
            .await()
    }

    /**
     * 获取首页置顶文章
     * @return ArrayList<Article>
     */
    suspend fun getTopArticles(): ArrayList<Article> {
        return RxHttp.get(ApiUrl.Home.TopArticles)
            .toApiResponse<ArrayList<Article>>()
            .await()
    }

    /**
     * 获取文章列表
     * @param page Int 页码 从0开始
     * @return ArrayList<Article>
     */
    suspend fun getArticle(page: Int): ArticleList {
        return RxHttp.get(ApiUrl.Home.Articles.format(page))
            .toApiResponse<ArticleList>()
            .await()
    }


}