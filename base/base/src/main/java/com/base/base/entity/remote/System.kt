package com.base.base.entity.remote

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-01 5:47 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */

const val TYPE_SYSTEM_TITLE = 1
const val TYPE_SYSTEM_ITEM = 2

data class SystemTree(
    val children: ArrayList<SystemTreeItem> = arrayListOf(),
    val articles: ArrayList<SystemTreeItem> = arrayListOf(),
    val id: Int = 0,
    val name: String = ""
)

data class SystemTreeItem(
    val id: Int = 0,
    val title: String = "",
    val name: String = "",
    val link: String = "",
    var multiType: Int = TYPE_SYSTEM_ITEM,
) {
    fun getContent() = if (title.isNotEmpty()) title else name
}
