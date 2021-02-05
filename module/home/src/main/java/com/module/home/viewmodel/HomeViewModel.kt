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

    fun getHomeData() {
        request(
            request = {
                // 轮播图
                val banner = HomeRepository.getBanner()
                // 博客列表
                val blogTree = HomeRepository.getBlogTree()
                // 置顶文章
                val topArticles = HomeRepository.getTopArticles()
                // 最新文章
                val newArticleList = HomeRepository.getNewArticles(0)
                // 首页文章
                val normalArticles = HomeRepository.getArticles(0)

                val articles: ArrayList<Article> = arrayListOf()
                if (blogTree.isNotEmpty()) {
                    val subList = if (blogTree.size > 7) blogTree.subList(0, 7) else blogTree
                    subList.add(ArticleTree(id = 9527, name = "更多"))
                    val data = subList.map { Article(itemType = HOME_TYPE_SYSTEM_GRID, id = it.id, title = it.name) }.toArrayList()
                    articles.addAll(data)
                }

                if (topArticles.isNotEmpty()) {
                    articles.add(Article(itemType = HOME_TYPE_TITLE, title = "置顶推荐"))
                    articles.addAll(topArticles)
                }

                if (!newArticleList.isEmpty()) {
                    articles.add(Article(itemType = HOME_TYPE_TITLE, title = "最新项目", hasMore = newArticleList.hasMore()))
                    articles.add(Article(itemType = HOME_TYPE_NEW_ARTICLE, newArticles = newArticleList.datas))
                }

                if (!normalArticles.isEmpty()) {
                    articles.add(Article(itemType = HOME_TYPE_TITLE, title = "首页文章"))
                    articles.addAll(normalArticles.datas)
                }
                normalArticles.datas.clear()
                normalArticles.datas.addAll(articles)
                Zip2(banner, normalArticles)
            },
            onSuccess = {
                bannerLiveData.postValue(it.first)
                articleLiveData.postValue(it.second)
            }
        )
    }

    fun getArticles(page: Int) {
        request(
            request = { HomeRepository.getArticles(page) },
            onSuccess = { articleLiveData.postValue(it) })
    }
}