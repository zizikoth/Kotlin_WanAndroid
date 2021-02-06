package com.base.base.manager

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.base.base.config.AppConfig
import com.base.base.entity.remote.User
import okhttp3.HttpUrl.Companion.toHttpUrl
import rxhttp.HttpSender
import rxhttp.wrapper.cookie.ICookieJar

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

    private val loginLiveData by lazy { MutableLiveData<User?>() }

    /**
     * 检查是否有缓存的Cookie
     */
    fun isLogin(): Boolean {
        val cookieJar = (HttpSender.getOkHttpClient().cookieJar as ICookieJar)
        val list = cookieJar.loadCookie(AppConfig.baseUrl.toHttpUrl())
        return !list.isNullOrEmpty()
    }

    /**
     * 清除所有请求Cookie
     */
    fun clearLogin() {
        (HttpSender.getOkHttpClient().cookieJar as ICookieJar).removeAllCookie()
    }

    /**
     * 通知页面登陆了
     */
    fun notifyLogin(user: User) {
        loginLiveData.postValue(user)
    }

    /**
     * 页面响应登陆
     */
    fun responseLogin(owner: LifecycleOwner, action: (User) -> Unit) {
        loginLiveData.observe(owner, { user -> user?.let { action.invoke(it) } })
    }

}