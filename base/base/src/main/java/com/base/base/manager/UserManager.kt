package com.base.base.manager

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

    var user: User? = null
        get() = DataManager.getUser()
        set(value) {
            field = value
            if (field != null) {
                DataManager.setUser(field!!)
            } else {
                DataManager.removeUser()
            }
        }

    /**
     * 退出登陆
     */
    fun loginOut(){
        user = null
        removeCookie()
    }

    /**
     * 判断是否以收藏
     * @param id Int 文章id
     * @return Boolean
     */
    fun hasCollected(id: Int) = user?.collectIds?.any { it == id } ?: false

    /**
     * 添加收藏id
     * @param id Int
     */
    fun addCollected(id: Int) {
        if (user?.collectIds?.contains(id) == false) {
            user?.collectIds?.add(id)
        }
    }

    /**
     * 移除收藏
     * @param id Int
     */
    fun removeCollected(id: Int) {
        user?.collectIds?.remove(id)
    }

    /**
     * 检查是否有缓存的Cookie
     */
    fun hasCookie(): Boolean {
        val cookieJar = (HttpSender.getOkHttpClient().cookieJar as ICookieJar)
        val list = cookieJar.loadCookie(AppConfig.baseUrl.toHttpUrl())
        return !list.isNullOrEmpty()
    }

    /**
     * 清除所有请求Cookie
     */
    private fun removeCookie() {
        (HttpSender.getOkHttpClient().cookieJar as ICookieJar).removeAllCookie()
    }

}