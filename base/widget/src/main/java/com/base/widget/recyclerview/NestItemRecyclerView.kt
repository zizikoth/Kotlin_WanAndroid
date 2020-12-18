package com.base.widget.recyclerview

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewConfiguration
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

/**
 * title:横纵相嵌套的RecyclerView 内层RecyclerView
 * tip:
 *
 * @author zhou
 * @date 2018/8/21 上午9:53
 */
open class NestItemRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : RecyclerView(context, attrs, defStyle) {
    private var mScrollPointerId = INVALID_POINTER
    private var mInitialTouchX: Int = 0
    private var mInitialTouchY: Int = 0
    private var mTouchSlop: Int = 0

    init {
        val vc = ViewConfiguration.get(getContext())
        mTouchSlop = vc.scaledTouchSlop
    }

    override fun setScrollingTouchSlop(slopConstant: Int) {
        super.setScrollingTouchSlop(slopConstant)
        val vc = ViewConfiguration.get(context)
        when (slopConstant) {
            TOUCH_SLOP_DEFAULT -> mTouchSlop = vc.scaledTouchSlop
            TOUCH_SLOP_PAGING -> mTouchSlop = vc.scaledPagingTouchSlop
            else -> {
            }
        }
    }

    override fun onInterceptTouchEvent(e: MotionEvent): Boolean {
        if (layoutManager == null) {
            return super.onInterceptTouchEvent(e)
        }
        when (e.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                mScrollPointerId = e.getPointerId(0)
                mInitialTouchX = (e.x + 0.5f).toInt()
                mInitialTouchY = (e.y + 0.5f).toInt()
                return super.onInterceptTouchEvent(e)
            }
            MotionEvent.ACTION_MOVE -> {
                val index = e.findPointerIndex(mScrollPointerId)
                if (index < 0) {
                    return false
                }

                val x = (e.getX(index) + 0.5f).toInt()
                val y = (e.getY(index) + 0.5f).toInt()
                if (scrollState != SCROLL_STATE_DRAGGING) {
                    val dx = x - mInitialTouchX
                    val dy = y - mInitialTouchY
                    val canScrollHorizontally = layoutManager!!.canScrollHorizontally()
                    val canScrollVertically = layoutManager!!.canScrollVertically()
                    var startScroll = false
                    if (canScrollHorizontally &&
                        abs(dx) > mTouchSlop &&
                        (abs(dx) >= abs(dy) || canScrollVertically)) {
                        startScroll = true
                    }
                    if (canScrollVertically &&
                        abs(dy) > mTouchSlop &&
                        (abs(dy) >= abs(dx) || canScrollHorizontally)) {
                        startScroll = true
                    }
                    return startScroll && super.onInterceptTouchEvent(e)
                }
                return super.onInterceptTouchEvent(e)
            }

            else -> return super.onInterceptTouchEvent(e)
        }
    }

    companion object {
        private const val INVALID_POINTER = -1
    }
}
