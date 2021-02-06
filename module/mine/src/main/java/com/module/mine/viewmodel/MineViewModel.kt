package com.module.mine.viewmodel

import androidx.lifecycle.MutableLiveData
import com.base.base.entity.remote.ArticleList
import com.base.base.entity.remote.CoinInfo
import com.base.base.entity.zip.Zip2
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

    val infoLiveData by lazy { MutableLiveData<Zip2<CoinInfo, ArticleList>>() }

    fun getUserInfo() {
        requestNoStatus(
            request = {
                val coin = MineRepository.getCoinAsync(it)
                val collectionList = MineRepository.getCollectionsAsync(it, 0)
                Zip2(coin.await(), collectionList.await())
            },
            onSuccess = { infoLiveData.postValue(it) }
        )
    }
}