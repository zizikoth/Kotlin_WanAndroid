package com.module.home.ui.fragment.home

import androidx.lifecycle.MutableLiveData
import com.base.base.entity.remote.ArticleList
import com.base.base.entity.remote.HomeBanner
import com.base.base.entity.zip.Zip2
import com.base.base.ui.mvvm.BaseViewModel
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
                val banner = HomeRepository.getBanner()
                val topArticles = HomeRepository.getTopArticles().onEach { it.top = true }
                val articles = HomeRepository.getArticle(0)
                articles.datas.addAll(0, topArticles)
                Zip2(banner, articles)
            },
            onSuccess = {
                bannerLiveData.postValue(it.first)
                articleLiveData.postValue(it.second)
            }
        )
    }

    fun getArticles(page: Int) {
        request(
            request = { HomeRepository.getArticle(page) },
            onSuccess = { articleLiveData.postValue(it) })
    }
}