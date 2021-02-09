package com.module.home.viewmodel

import androidx.lifecycle.MutableLiveData
import com.base.base.entity.remote.Article
import com.base.base.entity.remote.PageList
import com.base.base.ui.mvvm.BaseViewModel
import com.frame.core.utils.extra.toArrayList
import com.module.home.data.HomeLocalRepository
import com.module.home.data.HomeRepository

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-01-31 10:59 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class SearchViewModel : BaseViewModel() {

    val historyWordLiveData = MutableLiveData<ArrayList<String>>()
    val hotWordLiveData = MutableLiveData<ArrayList<String>>()


    val articleLiveData = MutableLiveData<PageList<Article>>()

    fun getAllWord() {
        requestNoStatus(
            request = { HomeRepository.getHotWord() },
            onSuccess = { hotWordLiveData.postValue(it.map { it.name }.toArrayList()) }
        )
        requestNoStatus(
            request = { HomeLocalRepository.getSearchWord() },
            onSuccess = { historyWordLiveData.postValue(it) }
        )
    }

    fun addSearch(word: String) {
        requestNoCheck { HomeLocalRepository.addSearchWord(word) }
    }

    fun clearSearch() {
        requestNoCheck { HomeLocalRepository.removeSearchWord() }
    }

    fun getArticleByWord(word: String, page: Int) {
        request(
            request = { HomeRepository.getArticleByWord(word, page) },
            onSuccess = { articleLiveData.postValue(it) },
            showLoading = true
        )
    }

}