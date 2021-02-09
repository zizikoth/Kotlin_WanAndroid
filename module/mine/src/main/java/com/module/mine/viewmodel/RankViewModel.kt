package com.module.mine.viewmodel

import androidx.lifecycle.MutableLiveData
import com.base.base.entity.remote.PageList
import com.base.base.entity.remote.RankInfo
import com.base.base.ui.mvvm.BaseViewModel
import com.module.mine.data.CoinRepository

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-08 4:02 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class RankViewModel : BaseViewModel() {

    val rankLiveData = MutableLiveData<PageList<RankInfo>>()

    fun getRank(page: Int) {
        request(
            request = { CoinRepository.getRank(page) },
            onSuccess = { rankLiveData.postValue(it) }
        )
    }
}