package com.module.mine.viewmodel

import com.base.base.manager.BusManager
import com.base.base.manager.UserManager
import com.base.base.ui.mvvm.BaseViewModel
import com.module.mine.data.UserRepository

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-06 4:01 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class LoginViewModel : BaseViewModel() {

    fun login(userName: String, password: String) {
        request(
            request = { UserRepository.login(userName, password) },
            onSuccess = {
                UserManager.setUser(it)
                BusManager.get().loginLiveData.postValue(it)
            },
            showLoading = true)
    }

    fun register(userName: String, password: String) {
        request(
            request = {
                // 先注册
                UserRepository.register(userName, password)
                // 注册成功后 登陆
                UserRepository.login(userName, password)
            },
            onSuccess = {
                UserManager.setUser(it)
                BusManager.get().loginLiveData.postValue(it)
            },
            showLoading = true
        )
    }

}