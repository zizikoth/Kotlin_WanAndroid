package com.base.base.api

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-01-30 2:33 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
object ApiUrl {
    object Home {
        /**
         * Banner图
         */
        const val Banner = "banner/json"

        /**
         * 置顶文章
         */
        const val TopArticles = "article/top/json"

        /**
         * 文章列表
         * %d 页码
         */
        const val Articles = "article/list/%d/json"
    }
}