package com.base.base.widget.recyclerview

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager

/**
 * title:不能纵向滑动的GridLayoutManager 防止滑动冲突
 * tip:
 *
 * @author zhou
 * @date 2018/8/21 上午9:54
 */
class NoScrollGridLayoutManager : GridLayoutManager {

    constructor(context: Context, spanCount: Int) : super(context, spanCount)

    constructor(context: Context, spanCount: Int, orientation: Int) : super(
        context,
        spanCount,
        orientation,
        false
    )

    override fun canScrollVertically(): Boolean {
        return false
    }

    override fun canScrollHorizontally(): Boolean {
        return false
    }
}
