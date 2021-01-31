package com.module.home.data

import com.blankj.utilcode.util.SPUtils
import com.frame.core.utils.extra.convert2List
import com.frame.core.utils.extra.convert2String
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-01-31 11:22 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
object HomeLocalRepository {

    private val SP by lazy { SPUtils.getInstance(SP_NAME) }

    private const val SP_NAME = "WanAndroid"

    private const val SEARCH_WORD = "SearchWord"

    suspend fun getSearchWord(): ArrayList<String> {
        return withContext(Dispatchers.IO) { SP.getString(SEARCH_WORD).convert2List() }
    }

    suspend fun addSearchWord(word: String) {
        withContext(Dispatchers.IO) {
            val list = SP.getString(SEARCH_WORD).convert2List()
            if (!list.contains(word)) {
                list.add(0, word)
                if (list.size > 20) list.removeLast()
                val str = list.convert2String()
                SP.put(SEARCH_WORD, str)
            }
        }
    }

    suspend fun clearSearchWord() {
        withContext(Dispatchers.IO) { SP.put(SEARCH_WORD, "") }
    }


}