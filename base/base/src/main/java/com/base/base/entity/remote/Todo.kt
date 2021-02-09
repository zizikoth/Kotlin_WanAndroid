package com.base.base.entity.remote

import android.os.Parcelable
import com.base.base.entity.local.TodoPriority
import com.base.base.entity.local.TodoStatus
import com.base.base.entity.local.TodoType
import kotlinx.parcelize.Parcelize

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-09 3:29 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
@Parcelize
data class TodoInfo(
    val id: Int = 0,
    val title: String = "",
    val content: String = "",
    val dateStr: String = "",
    val completeDateStr: String = "",
    val type: Int = 0,
    var status: Int = 0,
    val priority: Int = 0
) : Parcelable {
    fun getStatusContent() = when (status) {
        TodoStatus.COMPLETE.value -> "已完成"
        else -> "未完成"
    }

    fun getTypeContent() = when (type) {
        TodoType.WORK.value -> "工作"
        TodoType.LIFE.value -> "生活"
        TodoType.MINE.value -> "个人"
        TodoType.OTHER.value -> "其他"
        else -> "其他"
    }

    fun getPriorityContent() = when(priority){
        TodoPriority.IMMEDIATE.value -> "立刻马上！！！"
        TodoPriority.HIGH.value -> "比较重要！！"
        TodoPriority.NORMAL.value -> "一般般吧！"
        TodoPriority.LOW.value -> "无所谓的"
        else -> "一般般吧"
    }
}
