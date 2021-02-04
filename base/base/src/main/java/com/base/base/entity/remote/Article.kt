package com.base.base.entity.remote

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-01-30 3:02 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
data class ArticleList(
    val over: Boolean?,
    val datas: ArrayList<Article>
) {
    fun isEmpty() = datas.isNullOrEmpty()
    fun hasMore() = over == false
}

const val HOME_TYPE_TITLE = 1
const val HOME_TYPE_NEW_ARTICLE = 2
const val HOME_TYPE_NORMAL_ARTICLE = 3

data class Article(
    val author: String = "",
    val chapterName: String = "",
    val collect: Boolean = false,
    val desc: String = "",
    val envelopePic: String = "",
    val id: Int = 0,
    val link: String = "",
    val niceDate: String = "",
    val shareUser: String = "",
    val superChapterName: String = "",
    val title: String = "",
    val userId: Int = 0,
    val zan: Int = 0,
    // 自定义属性
    // 类型
    var itemType: Int = HOME_TYPE_NORMAL_ARTICLE,
    // 标题是否显示更多
    var hasMore: Boolean = false,
    var newArticles: ArrayList<Article> = arrayListOf()
) {
    fun getCurrentAuthor() = if (author.isNotEmpty()) {
        "作者：${author}"
    } else if (shareUser.isNotEmpty()) {
        "分享者：${shareUser}"
    } else {
        "匿名"
    }
}

data class ArticleTag(
    val name: String = "",
    val url: String = ""
)