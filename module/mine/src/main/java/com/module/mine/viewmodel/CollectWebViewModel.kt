package com.module.mine.viewmodel

import androidx.lifecycle.MutableLiveData
import com.base.base.entity.remote.WebInfo
import com.base.base.ui.mvvm.BaseViewModel
import com.kongzue.dialog.v3.FullScreenDialog
import com.business.common.data.CollectRepository

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-08 6:16 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class CollectWebViewModel : BaseViewModel() {

    val listLiveData by lazy { MutableLiveData<ArrayList<WebInfo>>() }
    val addLiveData by lazy { MutableLiveData<WebInfo>() }
    val updateLiveData by lazy { MutableLiveData<WebInfo>() }
    val deleteLiveData by lazy { MutableLiveData<Int>() }

    fun collectWebList() {
        request(
            request = { CollectRepository.collectWebList() },
            onSuccess = { listLiveData.postValue(it) }
        )
    }

    fun collectWebAdd(dialog: FullScreenDialog, name: String, link: String) {
        request(
            request = { CollectRepository.collectWebAdd(name, link) },
            onSuccess = {
                dialog.doDismiss()
                addLiveData.postValue(it)
            },
            showLoading = true
        )
    }

    fun collectWebUpdate(dialog: FullScreenDialog, id: Int, name: String, link: String) {
        request(
            request = { CollectRepository.collectWebUpdate(id, name, link) },
            onSuccess = {
                dialog.doDismiss()
                updateLiveData.postValue(it)
            },
            showLoading = true
        )
    }

    fun collectWebDelete(id: Int) {
        request(
            request = { CollectRepository.collectWebDelete(id) },
            onSuccess = { deleteLiveData.postValue(id) },
            showLoading = true
        )
    }
}