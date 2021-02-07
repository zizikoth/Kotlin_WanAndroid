package com.frame.core.utils

import android.app.Activity
import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.blankj.utilcode.util.KeyboardUtils

/**
 * title:软键盘帮助类 点击空白处隐藏软键盘
 * tip:
 *
 * @author zhou
 * @date 2018-11-16 上午8:40
 */
object KeyboardHelper {

    @JvmStatic
    fun showKeyboard(activity: Activity) {
        KeyboardUtils.showSoftInput(activity)
    }

    @JvmStatic
    fun hideKeyboard(activity: Activity) {
        KeyboardUtils.hideSoftInput(activity)
    }

    /**
     * 点击空白处隐藏软键盘
     * 在BaseActivity中的dispatchTouchEvent方法中添加
     *
     * override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
     *      if (clickBlank2HideKeyboard()) {
     *          KeyboardHelper.clickBlank2HideKeyboard(this, currentFocus, ev)
     *      }
     *      return super.dispatchTouchEvent(ev)
     *  }
     *
     */
    @JvmStatic
    fun clickBlank2HideKeyboard(activity: Activity, view: View?, event: MotionEvent?) {
        if (view == null || event == null) {
            return
        }
        if (event.action == MotionEvent.ACTION_DOWN) {
            if (isShouldHideKeyboard(view, event)) {
                val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }
    }

    private fun isShouldHideKeyboard(v: View, event: MotionEvent): Boolean {
        if (v is EditText) {
            val l = intArrayOf(0, 0)
            v.getLocationInWindow(l)
            val left = l[0]
            val top = l[1]
            val bottom = top + v.getHeight()
            val right = left + v.getWidth()
            return !(event.x > left && event.x < right && event.y > top && event.y < bottom)
        }
        return false
    }

    /**
     * 销毁软键盘
     * @param activity Activity
     */
    @JvmStatic
    fun onDestroy(activity: Activity) {
        KeyboardUtils.hideSoftInput(activity)
        KeyboardUtils.fixSoftInputLeaks(activity)
    }
}
