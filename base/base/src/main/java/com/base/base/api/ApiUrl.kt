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

    object Coin {
        /**
         * 个人积分
         */
        const val CoinInfo = "lg/coin/userinfo/json"

        /**
         * 积分获取列表
         */
        const val CoinHistory = "lg/coin/list/%d/json"

        /**
         * 积分排行榜
         * %d 从1开始
         */
        const val CoinRank = "coin/rank/%d/json"

    }

    object Collect {
        /**
         * 收藏列表
         */
        const val CollectionList = "lg/collect/list/%d/json"

        /**
         * 收藏
         */
        const val Collect = "lg/collect/%d/json"

        /**
         * 收藏文章
         */
        const val CollectArticle = "lg/collect/add/json"

        /**
         * 取消收藏 - 收藏列表
         */
        const val UnCollectInList = "lg/uncollect/%d/json"

        /**
         * 取消收藏 - 文章页面
         */
        const val UnCollectInArticle = "lg/uncollect_originId/%d/json"

        /**
         * 收藏网址列表
         */
        const val CollectWebList = "lg/collect/usertools/json"

        /**
         * 收藏网址添加
         */
        const val CollectWebAdd = "lg/collect/addtool/json"

        /**
         * 收藏网址修改
         */
        const val CollectWebUpdate = "lg/collect/updatetool/json"

        /**
         * 收藏网址删除
         */
        const val CollectWebDelete = "lg/collect/deletetool/json"
    }

    object Todo {
        /**
         * 清单列表
         */
        const val TodoList = "lg/todo/v2/list/%d/json"

        /**
         * 新增清单
         */
        const val TodoAdd = "lg/todo/add/json"

        /**
         * 删除清单
         */
        const val TodoDelete = "lg/todo/delete/%d/json"

        /**
         * 更新清单
         */
        const val TodoUpdate = "lg/todo/update/%d/json"

        /**
         * 更新清单状态
         */
        const val TodoUpdateStatus = "lg/todo/done/%d/json"
    }

    object Square {
        /**
         * 广场列表
         */
        const val SquareList = "user_article/list/%d/json"

        /**
         * 用户广场分享列表
         * %d 用户id
         * %d 页码
         */
        const val SquareUserShareList = "user/%d/share_articles/%d/json"
    }

    object Share {

    }
}