package com.base.base.widget.recyclerview


import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.TypedValue
import android.util.TypedValue.applyDimension
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.base.base.R

/**
 * title:RecyclerView分割线
 * describe:
 *
 *
 * RecyclerViewDivider divider = new RecyclerViewDivider.Builder(this)
 * .setStyle(RecyclerViewDivider.Style.BETWEEN)
 * .setDrawableRes(R.drawable-xhdpi.divider)
 * //.setColorRes(R.color.divider_gray)
 * .setHeight(1.5f)
 * .setMarginLeft(72)
 * .setMarginRight(8)
 * .build();
 * mRvList.addItemDecoration(divider);
 *
 * @author zhou
 * @date 2019-05-15 15:21
 */

class RecyclerItemDecoration constructor(private val mBuilder: Builder) :
    RecyclerView.ItemDecoration() {

    private val mBounds = Rect()
    private var mDivider: Drawable? = null
    private var mOrientation: Int = 0


    companion object {
        const val HORIZONTAL = LinearLayout.HORIZONTAL

        const val VERTICAL = LinearLayout.VERTICAL
    }


    init {
        setOrientation()
        setDividerDrawable()
    }

    /**
     * Set Divider Drawable
     */
    private fun setDividerDrawable() {
        val drawable = if (mBuilder.getDrawable() != null) {
            mBuilder.getDrawable()
        } else {
            DividerDrawable(mBuilder.getColor())
        }
        mDivider = drawable
    }

    /**
     * Set Divider Orientation
     */
    private fun setOrientation() {
        val orientation = mBuilder.getOrientation()
        require(!(orientation != HORIZONTAL && orientation != VERTICAL)) { "Invalid orientation. It should be either HORIZONTAL or VERTICAL" }
        mOrientation = orientation
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        if (parent.adapter == null) {
            return
        }
        val count = parent.adapter!!.itemCount
        if (mOrientation == VERTICAL) {
            val height = mDivider!!.intrinsicHeight
            if (position == 0 && mBuilder.isShowTopDivider) {
                outRect.set(0, height, 0, height)
            } else if (!needSkip(position, count)) {
                outRect.set(0, 0, 0, height)
            }
        } else {
            val width = mDivider!!.intrinsicWidth
            if (position == 0 && mBuilder.isShowTopDivider) {
                outRect.set(width, 0, width, 0)
            } else if (!needSkip(position, count)) {
                outRect.set(0, 0, width, 0)
            }
        }
    }

    private fun needSkip(position: Int, count: Int): Boolean {
        return position < mBuilder.getStartSkipCount() || position >= count - mBuilder.getStartSkipCount()
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (parent.layoutManager == null) {
            return
        }
        if (mOrientation == VERTICAL) {
            drawVertical(c, parent)
        } else if (mOrientation == HORIZONTAL) {
            drawHorizontal(c, parent)
        }
    }

    /**
     * Draw vertical list divider
     *
     * @param canvas canvas
     * @param parent RecyclerView
     */
    private fun drawVertical(canvas: Canvas, parent: RecyclerView) {
        canvas.save()
        val left: Int
        val right: Int
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && parent.clipToPadding) {
            left = parent.paddingLeft + mBuilder.getMarginLeft()
            right = parent.width - parent.paddingRight - mBuilder.getMarginRight()
            canvas.clipRect(left, parent.paddingTop, right, parent.height - parent.paddingBottom)
        } else {
            left = mBuilder.getMarginLeft()
            right = parent.width - mBuilder.getMarginRight()
        }

        val childCount = parent.childCount
        var top: Int
        var bottom: Int
        if (parent.adapter == null) {
            return
        }
        val count = parent.adapter!!.itemCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(child)
            if (needSkip(position, count)) {
                continue
            }
            if (parent.layoutManager == null) {
                return
            }
            parent.layoutManager!!.getDecoratedBoundsWithMargins(child, mBounds)
            bottom = mBounds.bottom + Math.round(child.translationY)
            top = bottom - mDivider!!.intrinsicHeight
            mDivider!!.setBounds(left, top, right, bottom)
            mDivider!!.draw(canvas)
        }

        if (childCount > 0 && mBuilder.isShowTopDivider) {
            val child = parent.getChildAt(0)
            if (parent.layoutManager == null) {
                return
            }
            parent.layoutManager!!.getDecoratedBoundsWithMargins(child, mBounds)
            top = mBounds.top + Math.round(child.translationY)
            bottom = top + mDivider!!.intrinsicHeight
            mDivider!!.setBounds(left, top, right, bottom)
            mDivider!!.draw(canvas)
        }
        canvas.restore()
    }

    /**
     * Draw horizontal list divider
     *
     * @param canvas canvas
     * @param parent RecyclerView
     */
    private fun drawHorizontal(canvas: Canvas, parent: RecyclerView) {
        canvas.save()
        val top: Int
        val bottom: Int
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && parent.clipToPadding) {
            top = parent.paddingTop + mBuilder.getMarginTop()
            bottom = parent.height - parent.paddingBottom - mBuilder.getMarginBottom()
            canvas.clipRect(parent.paddingLeft, top, parent.width - parent.paddingRight, bottom)
        } else {
            top = mBuilder.getMarginTop()
            bottom = parent.height - mBuilder.getMarginBottom()
        }

        val childCount = parent.childCount
        var left: Int
        var right: Int
        if (parent.adapter == null) {
            return
        }
        val count = parent.adapter!!.itemCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(child)
            if (needSkip(position, count)) {
                continue
            }
            if (parent.layoutManager == null) {
                return
            }
            parent.layoutManager!!.getDecoratedBoundsWithMargins(child, mBounds)
            right = mBounds.right + Math.round(child.translationX)
            left = right - mDivider!!.intrinsicWidth
            mDivider!!.setBounds(left, top, right, bottom)
            mDivider!!.draw(canvas)
        }
        if (childCount > 0 && mBuilder.isShowTopDivider) {
            val child = parent.getChildAt(0)
            if (parent.layoutManager == null) {
                return
            }
            parent.layoutManager!!.getDecoratedBoundsWithMargins(child, mBounds)
            left = mBounds.left + Math.round(child.translationX)
            right = left + mDivider!!.intrinsicWidth
            mDivider!!.setBounds(left, top, right, bottom)
            mDivider!!.draw(canvas)
        }
        canvas.restore()
    }

    /**
     * Divider Style
     */
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    annotation class Style {
        companion object {
            val END = 0
            val START = 1
            val BOTH = 2
            val BETWEEN = 3
        }
    }

    /**
     * RecyclerView Divider Builder
     */
    class Builder(private val mContext: Context) {
        private var mDrawable: Drawable? = null
        private var mOrientation =
            VERTICAL
        private var mHeight = 1
        private var mMarginLeft = 0
        private var mMarginRight = 0
        private var mMarginTop = 0
        private var mMarginBottom = 0
        private var mColor = -0xa0a0b
        private var mStartSkipCount = 0
        private var mEndSkipCount = 0

        @Style
        private var mStyle =
            Style.BETWEEN
        var isShowTopDivider = false
            private set

        /**
         * Set divider drawable-xhdpi
         * 设置分割线绘制Drawable
         *
         * @param drawable Divider drawable-xhdpi
         */
        fun setDrawable(drawable: Drawable): Builder {
            mDrawable = drawable
            return this
        }

        /**
         * Set divider drawable-xhdpi resource
         * 设置分割线绘制Drawable
         *
         * @param drawableRes Divider drawable-xhdpi resource
         */
        fun setDrawableRes(drawableRes: Int): Builder {
            mDrawable = ContextCompat.getDrawable(mContext, drawableRes)
            return this
        }

        /**
         * Set divider style
         * 设置分割线绘制类型
         *
         * @param style divider style
         * @see Style
         */
        fun setStyle(@Style style: Int): Builder {
            mStyle = style
            return this
        }

        /**
         * Set divider orientation
         * 设置分割线方向 默认Vertical
         *
         * @param orientation divider orientation
         */
        fun setOrientation(orientation: Int): Builder {
            mOrientation = orientation
            return this
        }

        /**
         * Set divider size
         * 设置分割线高度
         *
         * @param sizeDp divider size
         */
        fun setHeightDp(sizeDp: Float): Builder {
            return setHeight(TypedValue.COMPLEX_UNIT_DIP, sizeDp)
        }

        fun setHeightPx(sizePx: Float): Builder {
            return setHeight(TypedValue.COMPLEX_UNIT_PX, sizePx)
        }

        /**
         * Set divider height
         * 设置分割线的高度
         *
         * @param unit   divider height unit
         * @param height divider height
         */
        fun setHeight(unit: Int, height: Float): Builder {
            this.mHeight = getSizeValue(unit, height)
            return this
        }

        /**
         * Set divider margin left
         * 设置距离左部的距离
         *
         * @param marginLeftDp margin left value
         */
        fun setMarginLeftDp(marginLeftDp: Float): Builder {
            return setMarginLeft(TypedValue.COMPLEX_UNIT_DIP, marginLeftDp)
        }

        fun setMarginLeftPx(marginLeftPx: Float): Builder {
            return setMarginLeft(TypedValue.COMPLEX_UNIT_PX, marginLeftPx)
        }

        /**
         * Set divider margin left
         * 设置距离左部的距离
         *
         * @param unit       margin left value unit
         * @param marginLeft margin left value
         */
        fun setMarginLeft(unit: Int, marginLeft: Float): Builder {
            this.mMarginLeft = getSizeValue(unit, marginLeft)
            return this
        }

        /**
         * Set divider margin right
         * 设置距离右部的距离
         *
         * @param marginRightDp margin right value
         */
        fun setMarginRightDp(marginRightDp: Float): Builder {
            return setMarginRight(TypedValue.COMPLEX_UNIT_DIP, marginRightDp)
        }

        fun setMarginRightPx(marginRightPx: Float): Builder {
            return setMarginRight(TypedValue.COMPLEX_UNIT_PX, marginRightPx)
        }

        /**
         * Set divider margin right
         * 设置距离右部的距离
         *
         * @param unit        margin right value unit
         * @param marginRight margin right value
         */
        fun setMarginRight(unit: Int, marginRight: Float): Builder {
            this.mMarginRight = getSizeValue(unit, marginRight)
            return this
        }

        /**
         * Set divider margin top
         * 设置距离顶部的距离
         *
         * @param marginTopDp margin top value
         */
        fun setMarginTopDp(marginTopDp: Float): Builder {
            return setMarginTop(TypedValue.COMPLEX_UNIT_DIP, marginTopDp)
        }

        fun setMarginTopPx(marginTopPx: Float): Builder {
            return setMarginTop(TypedValue.COMPLEX_UNIT_PX, marginTopPx)
        }

        /**
         * Set divider margin right
         * 设置距离顶部的距离
         *
         * @param unit      margin right value unit
         * @param marginTop margin top value
         */
        fun setMarginTop(unit: Int, marginTop: Float): Builder {
            mMarginTop = getSizeValue(unit, marginTop)
            return this
        }

        /**
         * Set divider margin bottom
         * 设置距离底部的距离
         *
         * @param marginBottomDp margin bottom value
         */
        fun setMarginBottomDp(marginBottomDp: Float): Builder {
            return setMarginBottom(TypedValue.COMPLEX_UNIT_DIP, marginBottomDp)
        }

        fun setMarginBottomPx(margonBottomPx: Float): Builder {
            return setMarginBottom(TypedValue.COMPLEX_UNIT_PX, margonBottomPx)
        }

        /**
         * Set divider margin bottom
         * 设置距离底部的距离
         *
         * @param unit         margin bottom value unit
         * @param marginBottom margin bottom value
         */
        fun setMarginBottom(unit: Int, marginBottom: Float): Builder {
            this.mMarginBottom = getSizeValue(unit, marginBottom)
            return this
        }

        /**
         * Set divider color
         * 设置分割线颜色
         *
         * @param color divider color
         */
        fun setColor(@ColorInt color: Int): Builder {
            mColor = color
            return this
        }

        /**
         * Set divider color
         * 设置分割线颜色
         *
         * @param colorRes divider color resource
         */
        fun setColorRes(@ColorRes colorRes: Int): Builder {
            mColor = ContextCompat.getColor(mContext, colorRes)
            return this
        }

        /**
         * Set skip count from start
         * 设置开始放弃绘制分割线的Item数量
         *
         * @param startSkipCount count from start
         */
        fun setStartSkipCount(startSkipCount: Int): Builder {
            mStartSkipCount = startSkipCount
            return this
        }

        /**
         * Set skip count before end
         * 设置末尾放弃绘制分割线的Item数量
         *
         * @param endSkipCount count before end
         */
        fun setEndSkipCount(endSkipCount: Int): Builder {
            mEndSkipCount = endSkipCount
            return this
        }

        fun getHeight() = mHeight

        fun getDrawable() = mDrawable

        fun getOrientation() = mOrientation

        fun getMarginTop() = mMarginTop

        fun getMarginBottom() = mMarginBottom

        fun getMarginLeft() = mMarginLeft

        fun getMarginRight() = mMarginRight

        fun getColor() = mColor

        fun getStartSkipCount() = mStartSkipCount

        fun getEndSkipCount() = mEndSkipCount

        fun getStyle() = mStyle

        private fun getSizeValue(unit: Int, size: Float): Int {
            return applyDimension(unit, size, mContext.resources.displayMetrics).toInt()
        }

        fun defaultBuild(): RecyclerItemDecoration {
            setColorRes(R.color.color_F5F5F5)
            setHeightDp(0.5f)
            return build()
        }

        fun build(): RecyclerItemDecoration {
            when (mStyle) {
                Style.BETWEEN -> mEndSkipCount++
                Style.BOTH -> mStartSkipCount--
                Style.END -> {
                }
                Style.START -> mEndSkipCount++
                else -> {
                }
            }
            isShowTopDivider = mStyle == Style.BOTH && mStartSkipCount < 0 || mStyle == Style.START
            return RecyclerItemDecoration(this)
        }
    }

    /**
     * DividerDrawable
     */
    private inner class DividerDrawable internal constructor(color: Int) : ColorDrawable(color) {

        override fun getIntrinsicWidth(): Int {
            return mBuilder.getHeight()
        }

        override fun getIntrinsicHeight(): Int {
            return mBuilder.getHeight()
        }
    }
}

