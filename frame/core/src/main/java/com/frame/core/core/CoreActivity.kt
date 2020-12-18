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
import com.frame.core.R
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
        overridePendingTransition(R.anim.slide_in_from_right, R.anim.activity_fade_hide)
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
        if (transparentStatusBar()) {
            // 状态栏透明
            BarUtils.setStatusBarColor(this, Color.TRANSPARENT, true)
        } else {
            // 状态栏颜色
            BarUtils.setStatusBarLightMode(this, false)
            BarUtils.setStatusBarColor(this, statusBarColor(), true)
            BarUtils.addMarginTopEqualStatusBarHeight(mBinding.root)
        }
    }

    /*** 分发点击事件 用来隐藏软键盘 ***/
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (clickBlank2HideKeyboard()) {
            KeyboardHelper.clickBlank2HideKeyboard(this, currentFocus, ev)
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.activity_fade_show, R.anim.slide_out_to_right)
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