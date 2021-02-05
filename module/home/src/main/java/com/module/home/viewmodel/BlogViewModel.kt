package com.module.home.viewmodel

import androidx.lifecycle.MutableLiveData
import com.base.base.entity.remote.ArticleList
import com.base.base.entity.remote.ArticleTree
import com.base.base.ui.mvvm.BaseViewModel
import com.module.home.data.HomeRepository

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-05 11:00 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class BlogViewModel : BaseViewModel() {

    val blogLiveData = MutableLiveData<ArrayList<ArticleTree>>()
    val articleLiveData = MutableLiveData<ArticleList>()

    fun getBlogTree() {
        request(
            request = { HomeRepository.getBlogTree() },
            onSuccess = { blogLiveData.postValue(it) }
        )
    }

    fun getBlogArticles(page: Int, cid: Int, word: String = "", showLoad: Boolean = false) {
        request(
            request = { HomeRepository.getBlogArticles(page, cid, word) },
            onSuccess = { articleLiveData.postValue(it) },
            showLoading = showLoad
        )
    }
}