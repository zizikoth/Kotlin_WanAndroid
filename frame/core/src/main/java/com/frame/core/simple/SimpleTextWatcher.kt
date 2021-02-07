package com.frame.core.simple

import android.text.Editable
import android.text.TextWatcher

/**
 * title:EditTextView 字数变化监听
 * describe:
 *
 * @author zhou
 * @date 2019-01-24 10:22
 */
open class SimpleTextWatcher : TextWatcher {

    override fun afterTextChanged(s: Editable?) {}

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
}