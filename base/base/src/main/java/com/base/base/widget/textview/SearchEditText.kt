package com.base.base.widget.textview


import android.content.Context
import android.util.AttributeSet
import com.blankj.utilcode.util.StringUtils
import com.ruffian.library.widget.REditText

/**
 * title: 搜索使用的EditText 用于一边输入一边搜索
 * describe:
 *
 * @author zhou
 * @date 2019-09-11 11:35
 */
class SearchEditText : REditText {

    private var limitDuration: Long = 800

    private var mListener: OnTextChangedListener? = null

    // 记录开始输入前的文本内容
    private var mStartText = ""

    private val mAction = Runnable {
        if (mListener != null) {
            // 判断最终和开始前是否一致
            if (!StringUtils.equals(mStartText, text!!.toString())) {
                mStartText = text!!.toString() // 更新 mStartText
                mListener!!.onTextChanged(mStartText)
            }
        }
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)


    fun setLimitDuration(duration: Long): SearchEditText {
        this.limitDuration = duration
        return this
    }

    /**
     * 在 limitDuration 时间内连续输入不触发文本变化
     */
    fun setOnTextChangedListener(onTextChanged: (text: String) -> Unit): SearchEditText {
        mListener = object : OnTextChangedListener {
            override fun onTextChanged(text: String) {
                onTextChanged(text)
            }
        }
        return this
    }

    override fun onTextChanged(
        text: CharSequence,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        // 移除上一次的回调
        removeCallbacks(mAction)
        postDelayed(mAction, limitDuration)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        removeCallbacks(mAction)
    }

    interface OnTextChangedListener {
        fun onTextChanged(text: String)
    }
}