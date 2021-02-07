package com.module.home.data

import com.base.base.manager.DataManager
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

    suspend fun getSearchWord(): ArrayList<String> {
        return withContext(Dispatchers.IO) { DataManager.getSearchWord() }
    }

    suspend fun addSearchWord(word: String) {
        withContext(Dispatchers.IO) { DataManager.addSearchWord(word) }
    }

    suspend fun removeSearchWord() {
        withContext(Dispatchers.IO) { DataManager.removeSearchWord() }
    }

}