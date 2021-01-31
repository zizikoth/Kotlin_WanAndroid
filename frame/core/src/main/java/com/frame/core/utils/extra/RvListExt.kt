package com.frame.core.utils.extra

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import androidx.viewpager2.widget.ViewPager2
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.constant.RefreshState
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener

var RecyclerView.supportsChangeAnimations: Boolean
    get() = (this.itemAnimator as SimpleItemAnimator).supportsChangeAnimations
    set(value) {
        (this.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = value
    }

var ViewPager2.enableOverScrollMode: Boolean
    get() = getChildAt(0).overScrollMode == View.OVER_SCROLL_NEVER
    set(value) {
        getChildAt(0).overScrollMode = if (value) View.OVER_SCROLL_ALWAYS else View.OVER_SCROLL_NEVER
    }

fun SmartRefreshLayout.onRefreshAndLoadMore(onRefresh: () -> Unit, onLoad: () -> Unit) {
    this.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
        override fun onRefresh(refreshLayout: RefreshLayout) {
            onRefresh.invoke()
        }

        override fun onLoadMore(refreshLayout: RefreshLayout) {
            onLoad.invoke()
        }
    })
}

fun SmartRefreshLayout.finish(hasMore: Boolean = true) {
    when (state) {
        RefreshState.Refreshing -> finishRefresh(400)
        RefreshState.Loading -> finishLoadMore(400)
        else -> {
        }
    }
    setEnableLoadMore(hasMore)
}