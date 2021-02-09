package com.module.mine.viewmodel

import com.base.base.manager.UserManager
import com.base.base.ui.mvvm.BaseViewModel
import com.module.mine.data.UserRepository

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-07 4:34 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class SettingViewModel : BaseViewModel() {

    fun loginOut(action: () -> Unit) {
        request(
            request = { UserRepository.loginOut() },
            onSuccess = {
                UserManager.loginOut()
                action.invoke()
            },
            showLoading = true
        )
    }
}