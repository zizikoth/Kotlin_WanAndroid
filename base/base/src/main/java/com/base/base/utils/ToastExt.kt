package com.base.base.utils

import android.view.Gravity
import android.widget.TextView
import com.base.base.R
import com.blankj.utilcode.util.ToastUtils

fun toast(message: Any?) {
    message?.let {
        if (it.toString().isNotEmpty()) {
            ToastUtils.showCustomShort(R.layout.layout_toast_view).findViewById<TextView>(R.id.mTvMessage).text = it.toString()
        }
    }
}
