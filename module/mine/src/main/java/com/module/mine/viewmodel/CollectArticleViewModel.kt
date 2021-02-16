package com.module.mine.viewmodel

import androidx.lifecycle.MutableLiveData
import com.base.base.entity.remote.Article
import com.base.base.entity.remote.PageList
import com.base.base.ui.mvvm.BaseViewModel
import com.kongzue.dialog.v3.FullScreenDialog
import com.business.common.data.CollectRepository

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
class CollectArticleViewModel : BaseViewModel() {

    val collectionListLiveData = MutableLiveData<PageList<Article>>()
    val unCollectLiveData = MutableLiveData<Int>()
    val collectArticleLiveData = MutableLiveData<Any?>()


    fun getCollectionList(page: Int) {
        request(
            request = { CollectRepository.collectListAsync(it, page).await() },
            onSuccess = { collectionListLiveData.postValue(it) }
        )
    }

    fun collect(dialog: FullScreenDialog, title: String, author: String, link: String) {
        request(
            request = { CollectRepository.collectOutsideArticle(title, author, link) },
            onSuccess = {
                dialog.doDismiss()
                collectArticleLiveData.postValue(null)
            },
            showLoading = true
        )
    }

    fun unCollect(id: Int, originId: Int) {
        request(
            request = { CollectRepository.unCollectInList(id, originId) },
            onSuccess = { unCollectLiveData.postValue(id) },
            showLoading = true
        )
    }

}