package com.module.mine.viewmodel

import androidx.lifecycle.MutableLiveData
import com.base.base.entity.remote.UserSquareShare
import com.base.base.ui.mvvm.BaseViewModel
import com.blankj.utilcode.util.LogUtils
import com.kongzue.dialog.v3.FullScreenDialog
import com.module.mine.data.ShareRepository

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-10 9:58 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class ShareViewModel : BaseViewModel() {

    val listLiveData by lazy { MutableLiveData<UserSquareShare>() }
    val resultLiveData by lazy { MutableLiveData<Int>() }

    fun getShareList(page: Int) {
        request(
            request = { ShareRepository.getShareList(page) },
            onSuccess = { listLiveData.postValue(it) }
        )
    }

    fun addShare(dialog: FullScreenDialog, title: String, link: String) {
        request(
            request = { ShareRepository.addShare(title, link) },
            onSuccess = {
                dialog.doDismiss()
                resultLiveData.postValue(-1)
            },
            showLoading = true
        )
    }

    fun deleteShare(id: Int) {
        request(
            request = { ShareRepository.deleteShare(id) },
            onSuccess = { resultLiveData.postValue(id) },
            showLoading = true
        )
    }
}