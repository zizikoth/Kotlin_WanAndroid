package com.base.base.widget.dragfloatactionbutton

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import com.base.base.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.math.sqrt

/**
 * title:可拖拽button
 * describe:
 *
 * @author memo
 * @date 2019-10-20 14:38
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class DragFloatingActionButton : FloatingActionButton {
    private var mLastRawX: Float = 0f
    private var mLastRawY: Float = 0f
    private var isDrag = false
    private var mRootMeasuredWidth = 0
    private var mRootMeasuredHeight = 0
    private var mRootTopY = 0


    private var enableAttach = true
    private var enableDrag = true
    private var limitMinTop = 0f
    private var margin = 10f

    constructor(context: Context)
            : this(context, null)

    constructor(context: Context, attrs: AttributeSet?)
            : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
        val attr = context.obtainStyledAttributes(attrs, R.styleable.DragFloatingActionButton)
        enableAttach = attr.getBoolean(R.styleable.DragFloatingActionButton_drag_enable_attach, enableAttach)
        enableDrag = attr.getBoolean(R.styleable.DragFloatingActionButton_drag_enable_drag, enableDrag)
        limitMinTop = attr.getDimension(R.styleable.DragFloatingActionButton_drag_limit_top, limitMinTop)
        margin = attr.getDimension(R.styleable.DragFloatingActionButton_drag_margin, margin)
        attr.recycle()
    }

    fun enableAttach(enable: Boolean) {
        this.enableAttach = enable
    }

    fun enableDrag(enable: Boolean) {
        this.enableDrag = enable
    }

    fun setLimitTop(top: Float) {
        this.limitMinTop = top
    }

    fun setMargin(margin: Float) {
        this.margin = margin
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent): Boolean {
        //判断是否需要滑动
        if (enableDrag) {
            //当前手指的坐标
            val mRawX = ev.rawX
            val mRawY = ev.rawY
            when (ev.action) {
                MotionEvent.ACTION_DOWN -> {
                    isDrag = false
                    //记录按下的位置
                    mLastRawX = mRawX
                    mLastRawY = mRawY
                    val mViewGroup = parent as ViewGroup?
                    if (mViewGroup != null) {
                        val location = IntArray(2)
                        mViewGroup.getLocationInWindow(location)
                        //获取父布局的高度
                        mRootMeasuredHeight = mViewGroup.measuredHeight
                        mRootMeasuredWidth = mViewGroup.measuredWidth
                        //获取父布局顶点的坐标
                        mRootTopY = location[1]
                    }
                }
                MotionEvent.ACTION_MOVE -> if (mRawX >= 0 && mRawX <= mRootMeasuredWidth && mRawY >= mRootTopY && mRawY <= mRootMeasuredHeight + mRootTopY) {
                    //手指X轴滑动距离
                    val dx = mRawX - mLastRawX
                    //手指Y轴滑动距离
                    val dy = mRawY - mLastRawY
                    //判断是否为拖动操作
                    if (!isDrag) {
                        isDrag = sqrt((dx * dx + dy * dy).toDouble()) >= 2
                    }
                    //理论中X轴拖动的距离
                    var endX = x + dx
                    //理论中Y轴拖动的距离
                    var endY = y + dy
                    //X轴可以拖动的最大距离
                    val maxX = (mRootMeasuredWidth - width).toFloat()
                    //Y轴可以拖动的最大距离
                    val maxY = (mRootMeasuredHeight - height).toFloat()
                    //X轴边界限制
                    endX = if (endX < margin) margin else if (endX > maxX) maxX - margin else endX
                    //Y轴边界限制
                    endY = if (endY < limitMinTop + margin) limitMinTop + margin else if (endY > maxY - margin) maxY - margin else endY
                    //开始移动
                    x = endX
                    y = endY
                    //记录位置
                    mLastRawX = mRawX
                    mLastRawY = mRawY
                }
                MotionEvent.ACTION_UP -> {
                    //根据自定义属性判断是否需要贴边
                    if (enableAttach && isDrag) {
                        val center = (mRootMeasuredWidth / 2).toFloat()
                        //自动贴边
                        if (mLastRawX <= center) {
                            //向左贴边
                            this@DragFloatingActionButton.animate()
                                .setInterpolator(BounceInterpolator())
                                .setDuration(500)
                                .x(margin)
                                .start()
                        } else {
                            //向右贴边
                            this@DragFloatingActionButton.animate()
                                .setInterpolator(BounceInterpolator())
                                .setDuration(500)
                                .x((mRootMeasuredWidth - width - margin))
                                .start()
                        }
                    }
                }
            }
        }
        //是否拦截事件
        return if (isDrag) {
            isPressed = false
            isDrag
        } else {
            super.onTouchEvent(ev)
        }
    }
}