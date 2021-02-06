package com.module.home.data

import com.base.base.api.ApiUrl
import com.base.base.entity.remote.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import rxhttp.async
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
    suspend fun getBannerAsync(scope: CoroutineScope): Deferred<ArrayList<HomeBanner>> {
        return RxHttp.get(ApiUrl.Home.Banner)
            .toApiResponse<ArrayList<HomeBanner>>()
            .async(scope)
    }

    /**
     * 获取博客名称
     * @return ArrayList<ArticleTree>
     */
    suspend fun getBlogTreeAsync(scope: CoroutineScope): Deferred<ArrayList<ArticleTree>> {
        return RxHttp.get(ApiUrl.Home.BlogTree)
            .toApiResponse<ArrayList<ArticleTree>>()
            .async(scope)
    }

    /**
     * 获取首页置顶文章
     * @return ArrayList<Article>
     */
    suspend fun getTopArticlesAsync(scope: CoroutineScope): Deferred<ArrayList<Article>> {
        return RxHttp.get(ApiUrl.Home.TopArticles)
            .toApiResponse<ArrayList<Article>>()
            .async(scope)
    }

    /**
     * 获取最新文章
     * @return ArticleList
     */
    suspend fun getNewArticlesAsync(scope: CoroutineScope, page: Int): Deferred<ArticleList> {
        return RxHttp.get(ApiUrl.Home.NewArticles, page)
            .toApiResponse<ArticleList>()
            .async(scope)
    }

    /**
     * 获取文章列表
     * @param page Int 页码 从0开始
     * @return ArrayList<Article>
     */
    suspend fun getArticlesAsync(scope: CoroutineScope, page: Int): Deferred<ArticleList> {
        return RxHttp.get(ApiUrl.Home.Articles, page)
            .toApiResponse<ArticleList>()
            .async(scope)
    }

    /**
     *
     * @param word String
     * @param page Int
     * @return ArticleList
     */
    suspend fun getArticleByWord(word: String, page: Int): ArticleList {
        return RxHttp.postForm(ApiUrl.Home.ArticlesByWord, page)
            .add("k", word)
            .toApiResponse<ArticleList>()
            .await()
    }

    /**
     * 获取搜索热词
     * @return ArrayList<HotWord>
     */
    suspend fun getHotWord(): ArrayList<HotWord> {
        return RxHttp.get(ApiUrl.Home.HotWord)
            .toApiResponse<ArrayList<HotWord>>()
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
        return RxHttp.get(ApiUrl.Home.BlogArticles, cid, page)
            .add("k", word)
            .toApiResponse<ArticleList>()
            .await()
    }

}