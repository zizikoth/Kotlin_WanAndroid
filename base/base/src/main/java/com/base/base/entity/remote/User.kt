package com.base.base.entity.remote

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-06 4:20 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
data class User(
    val id: Int,
    var collectIds: ArrayList<Int> = arrayListOf(),
    val nickname: String,
)