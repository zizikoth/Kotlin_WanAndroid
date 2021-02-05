package com.module.system.viewmodel

import androidx.lifecycle.MutableLiveData
import com.base.base.entity.remote.ArticleList
import com.base.base.entity.remote.SystemTreeItem
import com.base.base.entity.remote.TYPE_SYSTEM_TITLE
import com.base.base.ui.mvvm.BaseViewModel
import com.module.system.data.SystemRepository

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-01 4:50 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class SystemViewModel : BaseViewModel() {

    val systemLiveData = MutableLiveData<ArrayList<SystemTreeItem>>()
    val articleLiveData = MutableLiveData<ArticleList>()

    fun getSystemTree() {
        request(
            request = { SystemRepository.getSystemTree() },
            onSuccess = {
                val data = arrayListOf<SystemTreeItem>()
                it.forEach { tree ->
                    data.add(SystemTreeItem(tree.id, title = tree.name, multiType = TYPE_SYSTEM_TITLE))
                    data.addAll(tree.children)
                }
                systemLiveData.postValue(data)
            }
        )
    }

    fun getNaviTree() {
        request(
            request = { SystemRepository.getNaviTree() },
            onSuccess = {
                val data = arrayListOf<SystemTreeItem>()
                it.forEach { tree ->
                    data.add(SystemTreeItem(tree.id, title = tree.name, multiType = TYPE_SYSTEM_TITLE))
                    data.addAll(tree.articles)
                }
                systemLiveData.postValue(data)
            }
        )
    }

    fun getSystemArticle(page: Int, cid: Int) {
        request(
            request = { SystemRepository.getSystemArticles(page, cid) },
            onSuccess = { articleLiveData.postValue(it) }
        )
    }
}