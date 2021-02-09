package com.module.mine.viewmodel

import androidx.lifecycle.MutableLiveData
import com.base.base.entity.remote.CoinInfo
import com.base.base.entity.remote.PageList
import com.base.base.ui.mvvm.BaseViewModel
import com.module.mine.data.CoinRepository

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

    val coinLiveData = MutableLiveData<PageList<CoinInfo>>()

    fun getCoinList(page: Int) {
        request(
            request = { CoinRepository.getCoinHistory(page) },
            onSuccess = { coinLiveData.postValue(it) }
        )
    }
}