package com.module.mine.viewmodel

import com.base.base.manager.BusViewModel
import com.base.base.manager.UserManager
import com.base.base.ui.mvvm.BaseViewModel
import com.module.mine.data.MineRepository

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
            request = { MineRepository.login(userName, password) },
            onSuccess = {
                UserManager.setUser(it)
                BusViewModel.get().loginLiveData.postValue(it)
            },
            showLoading = true)
    }

    fun register(userName: String, password: String) {
        request(
            request = {
                // 先注册
                MineRepository.register(userName, password)
                // 注册成功后 登陆
                MineRepository.login(userName, password)
            },
            onSuccess = {
                UserManager.setUser(it)
                BusViewModel.get().loginLiveData.postValue(it)
            },
            showLoading = true
        )
    }

}