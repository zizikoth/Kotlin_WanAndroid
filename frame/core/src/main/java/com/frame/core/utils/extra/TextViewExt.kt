package com.frame.core.utils.extra

import android.widget.EditText
import android.widget.TextView

/**
 * title:TextView 拓展
 * describe:
 *
 * @author memo
 * @date 2020-08-12 16:43
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */

/**
 * 安全的设置控件文字
 */
var TextView.value: String
    get() = this.text?.toString()?.trim() ?: ""
    set(value) {
        this.text = value
    }

/**
 * 清除控件文字
 */
fun TextView.clear() {
    this.value = ""
}

/**
 * 设置EditText是否可输入
 */
fun EditText.editable(enable: Boolean) {
    this.isClickable = enable
    this.isEnabled = enable
    this.isFocusable = enable
    this.isFocusableInTouchMode = enable
    if (enable) this.setSelection(this.text.length)
    if (enable) requestFocus()
}

