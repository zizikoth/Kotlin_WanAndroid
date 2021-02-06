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
         * 最新项目
         */
        const val NewArticles = "article/listproject/0/json"

        /**
         * 置顶文章
         */
        const val TopArticles = "article/top/json"

        /**
         * 文章列表
         * %d 页码
         */
        const val Articles = "article/list/%d/json"

        /**
         * 更具关键字搜索文章列表
         * %d 页码
         */
        const val ArticlesByWord = "article/query/%d/json"

        /**
         * 搜索热词
         */
        const val HotWord = "hotkey/json"

        /**
         * 博客名称列表
         */
        const val BlogTree = "wxarticle/chapters/json"

        /**
         * 博客文章列表
         * %d cid
         * %d page
         */
        const val BlogArticles = "wxarticle/list/%d/%d/json"
    }

    object Project {
        /**
         * 项目类型列表
         */
        const val ProjectTree = "project/tree/json"

        /**
         * 项目类型文章列表
         * %d 页码
         * %d cid
         */
        const val ProjectArticles = "project/list/%d/json?cid=%d"
    }


    object System {
        /**
         * 体系列表
         */
        const val SystemTree = "tree/json"

        /**
         * 体系文章列表
         */
        const val SystemArticles = "article/list/%d/json"

        /**
         * 导航列表
         */
        const val NaviTree = "navi/json"
    }

    object Login {
        /**
         * 登录
         */
        const val Login = "user/login"

        /**
         * 注册
         */
        const val Register = "user/register"

        /**
         * 登出
         */
        const val LoginOut = "user/loginout/json"
    }

    object Mine {
        /**
         * 个人积分
         */
        const val Coin = "lg/coin/userinfo/json"

        /**
         * 积分列表
         */
        const val CoinList = "lg/coin/list/%d/json"
    }
}