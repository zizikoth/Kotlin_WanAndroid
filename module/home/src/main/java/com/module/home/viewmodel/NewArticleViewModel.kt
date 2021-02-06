package com.module.home.viewmodel

import androidx.lifecycle.MutableLiveData
import com.base.base.entity.remote.ArticleList
import com.base.base.ui.mvvm.BaseViewModel
import com.module.home.data.HomeRepository

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-05 11:46 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class NewArticleViewModel : BaseViewModel() {
    val articleLiveData = MutableLiveData<ArticleList>()

    fun getNewArticle(page: Int) {
        request(
            request = { HomeRepository.getNewArticlesAsync(it, page).await() },
            onSuccess = { articleLiveData.postValue(it) }
        )

    }
}