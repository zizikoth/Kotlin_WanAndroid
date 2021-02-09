package com.business.common.viewmodel

import androidx.lifecycle.MutableLiveData
import com.base.base.ui.mvvm.BaseViewModel
import com.business.common.data.CommonRepository

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-07 10:36 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class CollectViewModel : BaseViewModel() {

    val collectLiveData by lazy { MutableLiveData<Boolean>() }

    fun collect(id: Int) {
        request(
            request = { CommonRepository.collect(id) },
            onSuccess = { collectLiveData.postValue(true) }
        )
    }

    fun unCollectInArticle(id: Int) {
        request(
            request = { CommonRepository.unCollectInArticle(id) },
            onSuccess = { collectLiveData.postValue(false) }
        )
    }

    fun unCollectInList(id: Int, originId: Int) {
        request(
            request = { CommonRepository.unCollectInList(id, originId) },
            onSuccess = { collectLiveData.postValue(false) }
        )
    }
}
