package com.base.base.widget.recyclerview

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager

/**
 * title:不能滑动的LinearLayoutManager 防止滑动冲突
 * tip:
 *
 * @author zhou
 * @date 2018/8/21 上午9:54
 */
class NoScrollLinearLayoutManager(context: Context) : LinearLayoutManager(context) {

    override fun canScrollVertically(): Boolean {
        return false
    }

    override fun canScrollHorizontally(): Boolean {
        return false
    }
}
