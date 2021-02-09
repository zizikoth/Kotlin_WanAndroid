package com.module.mine.data

import com.base.base.api.ApiUrl
import com.base.base.entity.remote.Article
import com.base.base.entity.remote.PageList
import com.base.base.entity.remote.WebInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import rxhttp.async
import rxhttp.tryAwait
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toApiResponse

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-09 2:53 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
object CollectRepository {
    /**
     * 获取收藏列表
     * @param page Int 页码
     * @return Deferred<ArticleList>
     */
    suspend fun collectListAsync(scope: CoroutineScope, page: Int): Deferred<PageList<Article>> {
        return RxHttp.get(ApiUrl.Collect.CollectionList, page)
            .toApiResponse<PageList<Article>>()
            .async(scope)
    }

    /**
     * 收藏站外文章
     * @param title String 标题
     * @param author String 作者
     * @param link String 链接
     * @return Any?
     */
    suspend fun collectArticle(title: String, author: String, link: String): Any? {
        return RxHttp.postForm(ApiUrl.Collect.CollectArticle)
            .add("title", title)
            .add("author", author)
            .add("link", link)
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
     * 收藏网址列表
     * @return ArrayList<WebInfo>
     */
    suspend fun collectWebList(): ArrayList<WebInfo> {
        return RxHttp.get(ApiUrl.Collect.CollectWebList)
            .toApiResponse<ArrayList<WebInfo>>()
            .await()
    }

    /**
     * 收藏网址新增
     * @param name String 标题
     * @param link String 链接
     * @return WebInfo
     */
    suspend fun collectWebAdd(name: String, link: String): WebInfo {
        return RxHttp.postForm(ApiUrl.Collect.CollectWebAdd)
            .add("name", name)
            .add("link", link)
            .toApiResponse<WebInfo>()
            .await()
    }

    /**
     * 收藏网址修改
     * @param id Int 网址id
     * @param name String 标题
     * @param link String 链接
     * @return WebInfo
     */
    suspend fun collectWebUpdate(id: Int, name: String, link: String): WebInfo {
        return RxHttp.postForm(ApiUrl.Collect.CollectWebUpdate)
            .add("id", id)
            .add("name", name)
            .add("link", link)
            .toApiResponse<WebInfo>()
            .await()
    }

    /**
     * 网址收藏删除
     * @param id Int
     * @return Any?
     */
    suspend fun collectWebDelete(id: Int): Any? {
        return RxHttp.postForm(ApiUrl.Collect.CollectWebDelete)
            .add("id", id)
            .toApiResponse<Any>()
            .tryAwait()
    }
}