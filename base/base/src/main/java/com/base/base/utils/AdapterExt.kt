package com.base.base.utils

import android.content.Context
import com.base.base.widget.empty.EmptyView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.frame.core.utils.ClickHelper

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-01 9:24 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
fun BaseQuickAdapter<*, *>.showEmpty(context: Context, isEmpty: Boolean, tip: String = "暂无数据") {
    if (!this.hasEmptyView() && isEmpty) {
        this.setEmptyView(EmptyView(context).setTip(tip))
    }
}

fun <T> BaseQuickAdapter<T, *>.onItemClick(onItemClick: (data: T) -> Unit) {
    this.setOnItemClickListener { _, _, position ->
        if (ClickHelper.isNotFastClick) onItemClick.invoke(this.getItem(position))
    }
}

fun <T> BaseQuickAdapter<T, *>.onItemLongClick(onItemLongClick: (data: T) -> Unit) {
    this.setOnItemLongClickListener { _, _, position ->
        onItemLongClick.invoke(this.getItem(position))
        true
    }
}

fun <T> BaseQuickAdapter<T, *>.onMultiItemClick(onItemClick: (multiType: Int, data: T) -> Unit) {
    this.setOnItemClickListener { _, _, position ->
        if (ClickHelper.isNotFastClick) onItemClick.invoke(this.getItemViewType(position), this.getItem(position))
    }
}

fun <T> BaseQuickAdapter<T, *>.onMultiItemLongClick(onMultiItemLongClick: (multiType: Int, data: T) -> Unit) {
    this.setOnItemLongClickListener { _, _, position ->
        onMultiItemLongClick.invoke(this.getItemViewType(position), this.getItem(position))
        true
    }
}

fun <T> BaseQuickAdapter<T, *>.onItemChildClick(onItemChildClick: (viewId: Int, data: T) -> Unit) {
    this.setOnItemChildClickListener { _, view, position ->
        if (ClickHelper.isNotFastClick) onItemChildClick.invoke(view.id, this.getItem(position))
    }
}

fun <T> BaseQuickAdapter<T, *>.onItemChildLongClick(onItemChildLongClick: (viewId: Int, data: T) -> Unit) {
    this.setOnItemChildLongClickListener { _, view, position ->
        onItemChildLongClick.invoke(view.id, this.getItem(position))
        true
    }
}

fun <T> BaseQuickAdapter<T, *>.onMultiItemChildClick(onItemChildClick: (multiType: Int, viewId: Int, data: T) -> Unit) {
    this.setOnItemChildClickListener { _, view, position ->
        if (ClickHelper.isNotFastClick) onItemChildClick.invoke(this.getItemViewType(position), view.id, this.getItem(position))
    }
}

fun <T> BaseQuickAdapter<T, *>.onMultiItemChildLongClick(onItemChildLongClick: (multiType: Int, viewId: Int, data: T) -> Unit) {
    this.setOnItemChildLongClickListener { _, view, position ->
        onItemChildLongClick.invoke(this.getItemViewType(position), view.id, this.getItem(position))
        true
    }
}


