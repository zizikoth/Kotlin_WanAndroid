package com.base.base.entity.remote

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-06 5:27 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
data class CoinInfo(
    val coinCount: Int = 0,
    val rank: String = "",
    val desc: String = "",
    val reason: String = "",
    val userName: String = ""
)

data class CoinList(
    val over: Boolean?,
    val datas: ArrayList<CoinInfo>) {
    fun isEmpty() = datas.isNullOrEmpty()
    fun hasMore() = over == false
}

data class RankInfo(
    val coinCount: Int = 0,
    val username: String = "",
    val rank: String = ""
)

data class RankList(
    val over: Boolean?,
    val datas: ArrayList<RankInfo>) {
    fun isEmpty() = datas.isNullOrEmpty()
    fun hasMore() = over == false
}