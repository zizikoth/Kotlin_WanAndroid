package com.base.base.widget.textview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import com.base.base.R
import com.frame.core.utils.extra.sp2px

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-12-09 10:42 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class AverageTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var text = if (isInEditMode) "门A门a门1门#门" else ""
        set(value) {
            if (field != value) {
                field = value
                invalidate()
            }
        }

    var textColor = Color.BLACK
        set(value) {
            if (field != value) {
                field = value
                invalidate()
            }
        }

    var textSize = 15f.sp2px
        set(value) {
            if (field != value) {
                field = value
                requestLayout()
                invalidate()
            }
        }

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var textSpacing = 0f
    private val textBound = Rect()

    init {
        val attr = context.obtainStyledAttributes(attrs, R.styleable.AverageTextView)
        text = attr.getString(R.styleable.AverageTextView_average_text) ?: text
        textColor = attr.getColor(R.styleable.AverageTextView_average_text_color, textColor)
        textSize = attr.getDimensionPixelSize(R.styleable.AverageTextView_average_text_size, textSize)
        attr.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mPaint.textSize = textSize.toFloat()
        val height = mPaint.fontMetricsInt.bottom - mPaint.fontMetricsInt.top
        setMeasuredDimension(measuredWidth, height)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        var textWidth = 0f
        text.forEach {
            mPaint.getTextBounds(it.toString(), 0, 1, textBound)
            textWidth += textBound.width()
        }
        // 这里的6表示一个大概的数字，实际显示的效果来说6比较不错
        val defaultTextMargin = textSize / 6
        textSpacing = (measuredWidth - paddingLeft - paddingRight - textWidth - defaultTextMargin) / (text.length - 1f)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (text.isEmpty()) return
        mPaint.color = textColor
        mPaint.textSize = textSize.toFloat()
        var drawTextX = paddingLeft.toFloat()
        val drawTextY = -mPaint.fontMetrics.top
        text.toCharArray().forEachIndexed { _, char ->
            canvas.drawText(char.toString(), drawTextX, drawTextY, mPaint)
            mPaint.getTextBounds(char.toString(), 0, 1, textBound)
            drawTextX += (textBound.width() + textSpacing)
        }
    }

}