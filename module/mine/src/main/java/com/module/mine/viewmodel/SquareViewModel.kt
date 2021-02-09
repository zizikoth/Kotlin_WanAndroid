package com.module.mine.viewmodel

import androidx.lifecycle.MutableLiveData
import com.base.base.entity.remote.Article
import com.base.base.entity.remote.PageList
import com.base.base.entity.remote.UserSquareShare
import com.base.base.ui.mvvm.BaseViewModel
import com.module.mine.data.SquareRepository

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-10 2:20 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class SquareViewModel : BaseViewModel() {

    val articleLiveData by lazy { MutableLiveData<PageList<Article>>() }
    val shareLiveData by lazy { MutableLiveData<UserSquareShare>() }

    fun getSquareList(page: Int) {
        request(
            request = { SquareRepository.getSquareList(page) },
            onSuccess = { articleLiveData.postValue(it) }
        )
    }

    fun getSquareUserShareList(userId: Int, page: Int) {
        request(
            request = { SquareRepository.getSquareUserShareList(userId, page) },
            onSuccess = { shareLiveData.postValue(it) }
        )
    }
}