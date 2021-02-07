package com.module.mine.viewmodel

import androidx.lifecycle.MutableLiveData
import com.base.base.entity.remote.CoinList
import com.base.base.ui.mvvm.BaseViewModel
import com.module.mine.data.MineRepository

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-07 1:59 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class CoinViewModel : BaseViewModel() {

    val coinLiveData = MutableLiveData<CoinList>()

    fun getCoinList(page: Int) {
        request(
            request = { MineRepository.getCoinList(page) },
            onSuccess = { coinLiveData.postValue(it) }
        )
    }
}