package com.base.base.manager

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.base.base.entity.remote.User

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-06 4:25 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
object UserManager {
    var user: User? = null

    private val loginLiveData by lazy { MutableLiveData<User?>() }

    /**
     * 检查是否登陆
     */
    fun isLogin() = user != null

    /**
     * 通知页面登陆了
     */
    fun notifyLogin(user: User) {
        this.user = user
        loginLiveData.postValue(user)
    }

    /**
     * 页面响应登陆
     */
    fun responseLogin(owner: LifecycleOwner, action: (User) -> Unit) {
        loginLiveData.observe(owner, { user -> user?.let { action.invoke(it) } })
    }

}