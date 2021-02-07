package com.base.base.manager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.base.base.entity.remote.User
import com.frame.core.core.CoreApp
import com.frame.core.livedata.UnPeekLiveData

/**
 * title:通知管理
 * describe:
 *
 * @author memo
 * @date 2021-02-07 10:26 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class BusViewModel : ViewModel() {

    companion object {
        fun get() = ViewModelProvider((CoreApp.app as CoreApp)).get(BusViewModel::class.java)
    }

    /**
     * 登陆通知
     */
    val loginLiveData: UnPeekLiveData<User> by lazy { UnPeekLiveData<User>() }

    /**
     * 收藏通知
     */
    val collectionLiveData: UnPeekLiveData<String> by lazy { UnPeekLiveData<String>() }

}