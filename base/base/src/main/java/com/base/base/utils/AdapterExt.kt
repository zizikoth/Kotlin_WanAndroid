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
    if (!this.hasEmptyView() && isEmpty) this.setEmptyView(EmptyView(context).setTip(tip))
}

fun <T> BaseQuickAdapter<T, *>.onItemClick(onItemClick: (data: T) -> Unit) {
    this.setOnItemClickListener { _, _, position ->
        if (ClickHelper.isNotFastClick) onItemClick.invoke(this.getItem(position))
    }
}

fun <T> BaseQuickAdapter<T, *>.onMultiItemClick(onItemClick: (multiType: Int, data: T) -> Unit) {
    this.setOnItemClickListener { _, _, position ->
        if (ClickHelper.isNotFastClick) onItemClick.invoke(this.getItemViewType(position), this.getItem(position))
    }
}

fun <T> BaseQuickAdapter<T, *>.onItemChildClick(onItemChildClick: (viewId: Int, data: T) -> Unit) {
    this.setOnItemChildClickListener { _, view, position ->
        if (ClickHelper.isNotFastClick) onItemChildClick.invoke(view.id, this.getItem(position))
    }
}

fun <T> BaseQuickAdapter<T, *>.onMultiItemChildClick(onItemChildClick: (multiType: Int, viewId: Int, data: T) -> Unit) {
    this.setOnItemChildClickListener { _, view, position ->
        if (ClickHelper.isNotFastClick) onItemChildClick.invoke(this.getItemViewType(position), view.id, this.getItem(position))
    }
}


