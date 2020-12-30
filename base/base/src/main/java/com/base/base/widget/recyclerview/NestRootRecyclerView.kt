package com.base.base.widget.recyclerview

import android.content.Context
import android.util.AttributeSet

/**
 * title:横纵相嵌套的RecyclerView 外层RecyclerView
 * tip:
 *
 * @author zhou
 * @date 2018/8/21 上午9:54
 */
class NestRootRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) :
    NestItemRecyclerView(context, attrs, defStyle) {

    override fun requestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
        /* do nothing */
    }
}
