package com.module.mine.viewmodel

import androidx.lifecycle.MutableLiveData
import com.base.base.entity.remote.CoinInfo
import com.base.base.ui.mvvm.BaseViewModel
import com.module.mine.data.MineRepository

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-05 11:05 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class MineViewModel : BaseViewModel() {

    val coinLiveData by lazy { MutableLiveData<CoinInfo>() }

    fun getCoin() {
        requestNoStatus(
            request = { MineRepository.getCoin() },
            onSuccess = { coinLiveData.postValue(it) }
        )
    }
}