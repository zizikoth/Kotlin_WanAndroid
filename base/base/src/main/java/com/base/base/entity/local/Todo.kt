package com.base.base.entity.local

import com.base.base.utils.toast

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-09 2:20 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
enum class TodoType(val value: Int) {
    // 工作
    WORK(1),

    // 生活
    LIFE(2),

    // 个人
    MINE(3),

    // 其他
    OTHER(4),

    // 所有
    ALL(-1)
}

enum class TodoStatus(val value: Int) {
    // 未完成
    UN_COMPLETE(0),

    // 完成
    COMPLETE(1),

    // 全部
    ALL(-1)
}

enum class TodoPriority(val value: Int) {
    // 立即马上 急急急
    IMMEDIATE(4),

    // 高等级 比较重要
    HIGH(3),

    // 一般般 还行要做
    NORMAL(2),

    // 不重要 随便记一下
    LOW(1),

    // 全部
    ALL(-1)
}

enum class TodoOrderBy(val value: Int) {
    // 完成时间顺序
    COMPLETE_DATE_UP(1),

    // 完成时间逆序
    COMPLETE_DATE_DOWN(2),

    // 创建时间顺序
    CREATE_DATE_UP(3),

    // 创建时间逆序
    CREATE_DATE_DOWN(4)
}

data class TodoFilter(
    var page: Int = 1,
    var state: TodoStatus = TodoStatus.ALL,
    var type: TodoType = TodoType.ALL,
    var priority: TodoPriority = TodoPriority.ALL,
    var orderBy: TodoOrderBy = TodoOrderBy.CREATE_DATE_UP
)

data class TodoContent(
    var title: String = "",
    var content: String = "",
    var type: Int = TodoType.OTHER.value,
    var priority: Int = TodoPriority.NORMAL.value,
    var id: Int = 0,
    var status: Int = TodoStatus.UN_COMPLETE.value
) {
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

    fun getPriorityContent() = when (priority) {
        TodoPriority.IMMEDIATE.value -> "立刻马上！！！"
        TodoPriority.HIGH.value -> "比较重要！！"
        TodoPriority.NORMAL.value -> "一般般吧！"
        TodoPriority.LOW.value -> "无所谓的"
        else -> "一般般吧"
    }

    fun checkSuccess(): Boolean {
        return if (title.isEmpty()) {
            toast("请输入标题")
            false
        } else if (content.isEmpty()) {
            toast("请输入内容")
            false
        } else true
    }
}