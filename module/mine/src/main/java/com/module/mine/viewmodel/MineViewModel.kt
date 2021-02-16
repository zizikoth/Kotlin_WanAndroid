package com.module.mine.viewmodel

import androidx.lifecycle.MutableLiveData
import com.base.base.entity.remote.CoinInfo
import com.base.base.ui.mvvm.BaseViewModel
import com.module.mine.data.CoinRepository
import com.business.common.data.CollectRepository

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

    val collectLiveData by lazy { MutableLiveData<Int>() }
    val coinLiveData by lazy { MutableLiveData<CoinInfo>() }

    fun getUserCollect() {
        requestNoStatus(
            request = { CollectRepository.collectListAsync(it, 0).await() },
            onSuccess = { collectLiveData.postValue(it.total) }
        )
    }

    fun getUserCoin() {
        requestNoStatus(
            request = { CoinRepository.getCoinAsync(it).await() },
            onSuccess = { coinLiveData.postValue(it) }
        )
    }
}