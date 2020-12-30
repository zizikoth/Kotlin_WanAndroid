package com.frame.core.core

import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import androidx.annotation.ColorInt
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.LogUtils
import com.frame.core.utils.KeyboardHelper

/**
 * title:CoreActivity
 * describe:
 *
 * @author memo
 * @date 2020-12-18 10:31 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
abstract class CoreActivity<BD : ViewDataBinding> : AppCompatActivity() {

    protected val mContext: CoreActivity<BD> by lazy { this }

    protected lateinit var mBinding: BD

    /*** 是否点击空白处隐藏软键盘 ***/
    protected open fun clickBlank2HideKeyboard(): Boolean = true

    /*** 是否透明状态栏 ***/
    protected open fun transparentStatusBar(): Boolean = false

    /*** 设置状态栏颜色 ***/
    @ColorInt
    protected open fun statusBarColor(): Int = Color.WHITE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        doOnBefore()
        initialize()
    }

    @LayoutRes
    protected abstract fun bindLayoutRes(): Int

    protected abstract fun initialize()

    protected open fun doOnBefore() {
        mBinding = DataBindingUtil.setContentView(mContext, bindLayoutRes())
        mBinding.lifecycleOwner = this
        // 状态栏
        BarUtils.setStatusBarColor(this, Color.TRANSPARENT, false)
        BarUtils.setStatusBarLightMode(this, true)
        mBinding.root.fitsSystemWindows = !transparentStatusBar()

    }

    /*** 分发点击事件 用来隐藏软键盘 ***/
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (clickBlank2HideKeyboard()) {
            KeyboardHelper.clickBlank2HideKeyboard(this, currentFocus, ev)
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onResume() {
        super.onResume()
        LogUtils.iTag("TopPage", this::class.java.simpleName)
    }

    override fun onDestroy() {
        super.onDestroy()
        KeyboardHelper.onDestroy(mContext)
    }


}