package com.module.mine.viewmodel

import androidx.lifecycle.MutableLiveData
import com.base.base.entity.remote.ArticleList
import com.base.base.ui.mvvm.BaseViewModel
import com.module.mine.data.MineRepository

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-07 12:28 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class CollectionViewModel : BaseViewModel() {

    val collectionsLiveData = MutableLiveData<ArticleList>()
    val unCollectionLiveData = MutableLiveData<Int>()

    fun getCollectionList(page: Int) {
        request(
            request = { MineRepository.getCollectionsAsync(it, page).await() },
            onSuccess = { collectionsLiveData.postValue(it) }
        )
    }

    fun unCollect(id: Int, originId: Int) {
        request(
            request = { MineRepository.unCollectInList(id, originId) },
            onSuccess = { unCollectionLiveData.postValue(id) },
            showLoading = true
        )
    }

}