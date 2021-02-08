package com.module.mine.viewmodel

import androidx.lifecycle.MutableLiveData
import com.base.base.entity.remote.ArticleList
import com.base.base.ui.mvvm.BaseViewModel
import com.kongzue.dialog.v3.FullScreenDialog
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

    val collectionListLiveData = MutableLiveData<ArticleList>()
    val unCollectLiveData = MutableLiveData<Int>()
    val collectArticleLiveData = MutableLiveData<Any?>()


    fun getCollectionList(page: Int) {
        request(
            request = { MineRepository.getCollectionsAsync(it, page).await() },
            onSuccess = { collectionListLiveData.postValue(it) }
        )
    }

    fun collect(dialog: FullScreenDialog, title: String, author: String, link: String) {
        request(
            request = { MineRepository.collectArticle(title, author, link) },
            onSuccess = {
                dialog.doDismiss()
                collectArticleLiveData.postValue(null)
            },
            showLoading = true
        )
    }

    fun unCollect(id: Int, originId: Int) {
        request(
            request = { MineRepository.unCollectInList(id, originId) },
            onSuccess = { unCollectLiveData.postValue(id) },
            showLoading = true
        )
    }

}