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


data class Article(
    var top: Boolean = false,
    val apkLink: String = "",
    val audit: Int = 0,
    val author: String = "",
    val canEdit: Boolean = false,
    val chapterId: Int = 0,
    val chapterName: String = "",
    val collect: Boolean = false,
    val courseId: Int = 0,
    val desc: String = "",
    val descMd: String = "",
    val envelopePic: String = "",
    val fresh: Boolean = false,
    val host: String = "",
    val id: Int = 0,
    val link: String = "",
    val niceDate: String = "",
    val niceShareDate: String = "",
    val origin: String = "",
    val prefix: String = "",
    val projectLink: String = "",
    val publishTime: Long = 0,
    val realSuperChapterId: Int = 0,
    val selfVisible: Int = 0,
    val shareDate: Long = 0,
    val shareUser: String = "",
    val superChapterId: Int = 0,
    val superChapterName: String = "",
    val tags: List<ArticleTag> = listOf(),
    val title: String = "",
    val type: Int = 0,
    val userId: Int = 0,
    val visible: Int = 0,
    val zan: Int = 0
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