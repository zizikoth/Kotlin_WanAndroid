package com.module.mine.data

import com.base.base.api.ApiUrl
import com.base.base.entity.local.*
import com.base.base.entity.remote.PageList
import com.base.base.entity.remote.TodoInfo
import com.blankj.utilcode.util.TimeUtils
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
object TodoRepository {

    /**
     * 获取Todo列表
     * @param filter TodoFilter
     * @return PageList<TodoInfo>
     */
    suspend fun todoList(filter: TodoFilter): PageList<TodoInfo> {
        val request = RxHttp.get(ApiUrl.Todo.TodoList, filter.page)
        if (filter.state != TodoStatus.ALL) {
            request.add("status", filter.state.value)
        }
        if (filter.type != TodoType.ALL) {
            request.add("type", filter.type.value)
        }
        if (filter.priority != TodoPriority.ALL) {
            request.add("priority", filter.priority.value)
        }
        return request
            .add("orderby", filter.orderBy.value)
            .toApiResponse<PageList<TodoInfo>>()
            .await()
    }

    /**
     * 新增一个Todo
     * @return Any?
     */
    suspend fun todoAdd(content: TodoContent): TodoInfo {
        return RxHttp.postForm(ApiUrl.Todo.TodoAdd)
            .add("title", content.title)
            .add("content", content.content)
            .add("date", TimeUtils.getNowString(TimeUtils.getSafeDateFormat("yyyy-MM-dd")))
            .add("type", content.type)
            .add("priority", content.priority)
            .toApiResponse<TodoInfo>()
            .await()
    }

    /**
     * 更新Todo内容
     * @return TodoInfo
     */
    suspend fun todoUpdate(content: TodoContent): TodoInfo {
        return RxHttp.postForm(ApiUrl.Todo.TodoUpdate, content.id)
            .add("title", content.title)
            .add("content", content.content)
            .add("date", TimeUtils.getNowString(TimeUtils.getSafeDateFormat("yyyy-MM-dd")))
            .add("status", content.status)
            .add("type", content.type)
            .add("priority", content.priority)
            .toApiResponse<TodoInfo>()
            .await()
    }

    /**
     * 更新Todo状态
     * @param id Int
     * @param status TodoStatus
     * @return TodoInfo
     */
    suspend fun todoUpdateStatus(id: Int, status: TodoStatus): TodoInfo {
        return RxHttp.postForm(ApiUrl.Todo.TodoUpdateStatus, id)
            .add("status", status.value)
            .toApiResponse<TodoInfo>()
            .await()
    }

    /**
     * 删除todo
     * @param id Int
     * @return Any?
     */
    suspend fun todoDelete(id: Int): Any? {
        return RxHttp.postForm(ApiUrl.Todo.TodoDelete, id)
            .toApiResponse<Any>()
            .tryAwait()
    }
}