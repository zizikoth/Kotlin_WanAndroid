package com.module.blog.ui.fragment

import androidx.lifecycle.MutableLiveData
import com.base.base.entity.remote.ArticleList
import com.base.base.entity.remote.ArticleTree
import com.base.base.ui.mvvm.BaseViewModel
import com.module.blog.data.BlogRepository

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-01 11:03 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class BlogViewModel : BaseViewModel() {

    val blogLiveData = MutableLiveData<ArrayList<ArticleTree>>()
    val articleLiveData = MutableLiveData<ArticleList>()

    fun getBlogTree() {
        request(
            request = { BlogRepository.getBlogTree() },
            onSuccess = { blogLiveData.postValue(it) }
        )
    }

    fun getBlogArticles(page: Int, cid: Int, word: String = "", showLoad: Boolean = false) {
        request(
            request = { BlogRepository.getBlogArticles(page, cid, word) },
            onSuccess = { articleLiveData.postValue(it) },
            showLoading = showLoad
        )
    }
}