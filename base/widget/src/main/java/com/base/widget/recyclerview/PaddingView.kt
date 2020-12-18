package com.base.widget.recyclerview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import com.frame.core.utils.extra.widthAndHeight

/**
 * title:作为头布局进行Padding
 * describe:
 *
 * @author memo
 * @date 2020-12-18 4:02 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class PaddingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    fun height(height: Int): PaddingView {
        widthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, height)
        return this
    }

    fun color(@ColorInt color: Int): PaddingView {
        setBackgroundColor(color)
        return this
    }
}