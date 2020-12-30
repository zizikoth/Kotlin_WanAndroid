package com.frame.core.dialog

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FixDialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.blankj.utilcode.util.KeyboardUtils
import com.blankj.utilcode.util.LogUtils

/**
 * title:BaseDialog
 * describe:
 *
 * @author memo
 * @date 2020-12-18 11:57 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
abstract class BaseDialog<BD : ViewDataBinding> : FixDialogFragment(), LifecycleObserver {

    protected lateinit var binding: BD

    /*** 绑定布局 ***/
    @LayoutRes
    protected abstract fun bindLayoutRes(): Int

    /*** 窗体动画 ***/
    @StyleRes
    protected abstract fun bindAnimStyleRes(): Int

    /*** 设置窗体配置 ***/
    protected abstract fun bindParams(params: WindowManager.LayoutParams)

    /*** 设置子控件 ***/
    protected abstract fun bindView(binding: BD)

    /*** 绑定Tag ***/
    open fun bindTag() = this::class.java.simpleName

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,bindLayoutRes(),container,false)
        binding.lifecycleOwner = this
        bindView(binding)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        lifecycle.addObserver(this)

        // 设置点击不消失
        dialog?.let {
            it.setCancelable(false)
            it.setCanceledOnTouchOutside(false)
            it.setOnCancelListener(null)
            it.setOnDismissListener(null)
        }

        // 设置窗体配置
        dialog?.window?.let {
            it.setWindowAnimations(bindAnimStyleRes())
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            it.attributes = it.attributes.apply {
                dimAmount = 0.4f
                bindParams(this)
            }
        }
    }

    fun show(manager: FragmentManager) {
        show(manager, bindTag())
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onLifecycleDestroy() {
        if (context is Activity) {
            val activity = context as Activity
            if (KeyboardUtils.isSoftInputVisible(activity)) {
                KeyboardUtils.hideSoftInput(activity)
            }
        }
        lifecycle.removeObserver(this)
        dismiss()
        LogUtils.iTag("BaseDialog", "解绑生命周期")
    }

}