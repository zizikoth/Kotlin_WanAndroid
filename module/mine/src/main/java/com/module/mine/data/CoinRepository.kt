package com.module.mine.data

import com.base.base.api.ApiUrl
import com.base.base.entity.remote.CoinInfo
import com.base.base.entity.remote.PageList
import com.base.base.entity.remote.RankInfo
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
 * @date 2021-02-09 2:54 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
object CoinRepository {

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
     * 获取积分获取记录
     * @param page Int 页码
     * @return PageList<CoinInfo>
     */
    suspend fun getCoinHistory(page: Int): PageList<CoinInfo> {
        return RxHttp.get(ApiUrl.Coin.CoinHistory, page)
            .toApiResponse<PageList<CoinInfo>>()
            .await()
    }

    /**
     * 获取积分排名
     * @param page Int
     * @return PageList<RankInfo>
     */
    suspend fun getRank(page: Int): PageList<RankInfo> {
        return RxHttp.get(ApiUrl.Coin.CoinRank, page)
            .toApiResponse<PageList<RankInfo>>()
            .await()
    }
}