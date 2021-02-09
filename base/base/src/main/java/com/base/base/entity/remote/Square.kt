package com.base.base.entity.remote

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-10 2:14 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
data class UserSquareShare(
    val coinInfo: CoinInfo,
    val shareArticles:PageList<Article>
)