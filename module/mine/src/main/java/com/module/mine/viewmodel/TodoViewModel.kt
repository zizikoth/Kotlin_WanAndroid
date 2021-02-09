package com.module.mine.viewmodel

import androidx.lifecycle.MutableLiveData
import com.base.base.entity.local.TodoContent
import com.base.base.entity.local.TodoFilter
import com.base.base.entity.local.TodoStatus
import com.base.base.entity.remote.PageList
import com.base.base.entity.remote.TodoInfo
import com.base.base.ui.mvvm.BaseViewModel
import com.module.mine.data.TodoRepository

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-09 11:20 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class TodoViewModel : BaseViewModel() {

    val listLiveData by lazy { MutableLiveData<PageList<TodoInfo>>() }
    val statusLiveData by lazy { MutableLiveData<TodoInfo>() }
    val deleteLiveData by lazy { MutableLiveData<Int>() }
    val resultLiveData by lazy { MutableLiveData<TodoInfo>() }

    fun getTodoList(filter: TodoFilter) {
        request(
            request = { TodoRepository.todoList(filter) },
            onSuccess = { listLiveData.postValue(it) }
        )
    }

    fun setTodoStatus(info: TodoInfo) {
        val status = if (info.status == TodoStatus.COMPLETE.value) TodoStatus.UN_COMPLETE else TodoStatus.COMPLETE
        request(
            request = { TodoRepository.todoUpdateStatus(info.id, status) },
            onSuccess = { statusLiveData.postValue(it) },
            showLoading = true
        )
    }

    fun deleteTodo(info: TodoInfo) {
        request(
            request = { TodoRepository.todoDelete(info.id) },
            onSuccess = { deleteLiveData.postValue(info.id) },
            showLoading = true
        )
    }

    fun addTodo(info: TodoContent) {
        request(
            request = { TodoRepository.todoAdd(info) },
            onSuccess = { resultLiveData.postValue(it) },
            showLoading = true
        )
    }

    fun updateTodo(info: TodoContent) {
        request(
            request = { TodoRepository.todoUpdate(info) },
            onSuccess = { resultLiveData.postValue(it) },
            showLoading = true
        )
    }

}