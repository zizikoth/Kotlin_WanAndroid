package com.base.base.manager

import com.base.base.entity.remote.User
import com.blankj.utilcode.util.SPUtils
import com.frame.core.utils.GsonHelper
import com.frame.core.utils.extra.convert2List
import com.frame.core.utils.extra.convert2String

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-07 3:57 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
object DataManager {
    private val SP by lazy { SPUtils.getInstance(SP_NAME) }

    private const val SP_NAME = "WanAndroid"

    private const val USER = "User"
    private const val SEARCH_WORD = "SearchWord"

    // ------------------------------- 用户 -------------------------------//
    fun getUser(): User? {
        val json = SP.getString(USER)
        return if (json.isNullOrEmpty()) null else GsonHelper.parse2Bean(json)
    }

    fun setUser(user: User) {
        SP.put(USER, GsonHelper.parse2Json(user))
    }

    fun removeUser() {
        SP.put(USER, "")
    }

    // ------------------------------- 搜索 -------------------------------//
    fun getSearchWord(): ArrayList<String> {
        return SP.getString(SEARCH_WORD).convert2List()
    }

    fun addSearchWord(word: String) {
        val list = SP.getString(SEARCH_WORD).convert2List()
        if (!list.contains(word)) {
            list.add(0, word)
            if (list.size > 20) list.removeLast()
            val str = list.convert2String()
            SP.put(SEARCH_WORD, str)
        }
    }

    fun removeSearchWord() {
        SP.put(SEARCH_WORD, "")
    }


}