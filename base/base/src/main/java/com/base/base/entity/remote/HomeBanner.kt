package com.base.base.entity.remote

/**
 * title:首页Banner
 * describe:
 *
 * @author memo
 * @date 2021-01-30 2:23 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
data class HomeBanner (
    var desc: String = "",
    var id: Int = 0,
    var imagePath: String = "",
    var isVisible: Int = 0,
    var order: Int = 0,
    var title: String = "",
    var type: Int = 0,
    var url: String = ""
)