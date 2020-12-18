package com.memo.core

import androidx.lifecycle.MutableLiveData
import com.base.base.ui.mvvm.BaseViewModel

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-12-19 1:59 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class MainViewModel : BaseViewModel() {

    val bannerLiveData = MutableLiveData<ArrayList<HomeBanner>>()

    fun getBanner() {
        request(
            request = { MainRepository.getBanner() },
            onSuccess = { bannerLiveData.postValue(it) }
        )
    }
}