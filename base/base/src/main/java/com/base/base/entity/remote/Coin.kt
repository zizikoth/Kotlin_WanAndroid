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
    val userId:Int = 0,
    val coinCount: Int = 0,
    val rank: String = "",
    val desc: String = "",
    val reason: String = "",
    val userName:String = ""
)

data class RankInfo(
    val coinCount: Int = 0,
    val username: String = "",
    val rank: String = ""
)