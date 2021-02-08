package com.module.mine.data

import com.base.base.api.ApiUrl
import com.base.base.entity.remote.*
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
 * @date 2021-02-06 4:39 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
object MineRepository {

    /**
     * 登陆
     * @param userName String 用户名
     * @param password String 密码
     * @return User
     */
    suspend fun login(userName: String, password: String): User {
        return RxHttp.postForm(ApiUrl.Login.Login)
            .add("username", userName)
            .add("password", password)
            .toApiResponse<User>()
            .await()
    }

    /**
     * 注册
     * @param userName String 用户名
     * @param password String 密码
     * @return Any?
     */
    suspend fun register(userName: String, password: String): Any? {
        return RxHttp.postForm(ApiUrl.Login.Register)
            .add("username", userName)
            .add("password", password)
            .add("repassword", password)
            .toApiResponse<Any>()
            .tryAwait()
    }

    /**
     * 登出
     * @return Any?
     */
    suspend fun loginOut(): Any? {
        return RxHttp.get(ApiUrl.Login.LoginOut)
            .toApiResponse<Any>()
            .tryAwait()
    }

    /**
     * 获取个人积分信息
     * @return CoinInfo
     */
    suspend fun getCoinAsync(scope: CoroutineScope): Deferred<CoinInfo> {
        return RxHttp.get(ApiUrl.Coin.CoinInfo)
            .toApiResponse<CoinInfo>()
            .async(scope)
    }


    /**
     * 获取收藏列表
     * @param page Int 页码
     * @return Deferred<ArticleList>
     */
    suspend fun getCollectionsAsync(scope: CoroutineScope, page: Int): Deferred<ArticleList> {
        return RxHttp.get(ApiUrl.Collect.CollectionList, page)
            .toApiResponse<ArticleList>()
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
     * 获取积分获取记录
     * @param page Int 页码
     * @return CoinList
     */
    suspend fun getCoinHistory(page: Int): CoinList {
        return RxHttp.get(ApiUrl.Coin.CoinHistory, page)
            .toApiResponse<CoinList>()
            .await()
    }

    /**
     * 获取积分排名
     * @param page Int
     * @return RankList
     */
    suspend fun getRank(page: Int): RankList {
        return RxHttp.get(ApiUrl.Coin.CoinRank, page)
            .toApiResponse<RankList>()
            .await()
    }
}