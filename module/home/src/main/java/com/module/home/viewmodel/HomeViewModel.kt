package com.module.home.viewmodel

import androidx.lifecycle.MutableLiveData
import com.base.base.entity.remote.*
import com.base.base.entity.zip.Zip2
import com.base.base.ui.mvvm.BaseViewModel
import com.frame.core.utils.extra.toArrayList
import com.module.home.data.HomeRepository

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-01-30 2:11 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class HomeViewModel : BaseViewModel() {

    val bannerLiveData = MutableLiveData<ArrayList<HomeBanner>>()
    val articleLiveData = MutableLiveData<ArticleList>()
    var start = 0L

    fun getHomeData() {
        request(
            request = {
                // 轮播图
                val banner = HomeRepository.getBannerAsync(it)
                // 博客列表
                val blogTree = HomeRepository.getBlogTreeAsync(it)
                // 置顶文章
                val topArticles = HomeRepository.getTopArticlesAsync(it)
                // 最新文章
                val newArticleList = HomeRepository.getNewArticlesAsync(it, 0)
                // 首页文章
                val normalArticles = HomeRepository.getArticlesAsync(it, 0)

                val articles: ArrayList<Article> = arrayListOf()

                blogTree.await().apply {
                    if (this.isNotEmpty()) {
                        val subList = if (this.size > 7) this.subList(0, 7) else this
                        subList.add(ArticleTree(id = 9527, name = "更多"))
                        val data = subList.map { Article(itemType = HOME_TYPE_SYSTEM_GRID, id = it.id, title = it.name) }.toArrayList()
                        articles.addAll(data)
                    }
                }

                topArticles.await().apply {
                    if (this.isNotEmpty()) {
                        articles.add(Article(itemType = HOME_TYPE_TITLE, title = "置顶推荐"))
                        articles.addAll(this)
                    }
                }

                newArticleList.await().apply {
                    if (!this.isEmpty()) {
                        articles.add(Article(itemType = HOME_TYPE_TITLE, title = "最新项目", hasMore = this.hasMore()))
                        articles.add(Article(itemType = HOME_TYPE_NEW_ARTICLE, newArticles = this.datas))
                    }
                }

                val data = normalArticles.await().apply {
                    if (!this.isEmpty()) {
                        articles.add(Article(itemType = HOME_TYPE_TITLE, title = "首页文章"))
                        articles.addAll(this.datas)
                    }

                    this.datas.clear()
                    this.datas.addAll(articles)
                }

                Zip2(banner.await(), data)
            },
            onSuccess = {
                bannerLiveData.postValue(it.first)
                articleLiveData.postValue(it.second)
            })
    }

    fun getArticles(page: Int) {
        request(
            request = { HomeRepository.getArticlesAsync(it, page).await() },
            onSuccess = { articleLiveData.postValue(it) })
    }
}