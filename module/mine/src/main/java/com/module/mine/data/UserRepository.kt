package com.module.mine.data

import com.base.base.api.ApiUrl
import com.base.base.entity.remote.User
import rxhttp.tryAwait
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toApiResponse

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-09 2:53 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
object UserRepository {

    /**
     * 登陆
     * @param userName String 用户名
     * @param password String 密码
     * @return User
     */
    suspend fun login(userName: String, password: String): User {
        return RxHttp.postForm(ApiUrl.Login.Login)
            .add("username", userName)
            .add("password", password)
            .toApiResponse<User>()
            .await()
    }

    /**
     * 注册
     * @param userName String 用户名
     * @param password String 密码
     * @return Any?
     */
    suspend fun register(userName: String, password: String): Any? {
        return RxHttp.postForm(ApiUrl.Login.Register)
            .add("username", userName)
            .add("password", password)
            .add("repassword", password)
            .toApiResponse<Any>()
            .tryAwait()
    }

    /**
     * 登出
     * @return Any?
     */
    suspend fun loginOut(): Any? {
        return RxHttp.get(ApiUrl.Login.LoginOut)
            .toApiResponse<Any>()
            .tryAwait()
    }

}