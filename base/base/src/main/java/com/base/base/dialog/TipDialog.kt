package com.base.base.dialog

import android.app.Dialog
import android.view.Gravity
import android.view.WindowManager
import com.base.base.R
import com.base.base.databinding.DialogTipBinding
import com.frame.core.dialog.BaseDialog
import com.frame.core.utils.extra.dimen
import com.frame.core.utils.extra.onClick

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-12-18 4:40 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class TipDialog(
    private val title: String = "提示", private val message: String = "内容",
    private val positive: String = "确定", private val negative: String = "取消"
) : BaseDialog<DialogTipBinding>() {

    private var onPositive: () -> Unit = {}
    private var onNegative: () -> Unit = {}

    /*** 绑定布局 ***/
    override fun bindLayoutRes(): Int = R.layout.dialog_tip

    /*** 窗体动画 ***/
    override fun bindAnimStyleRes(): Int = R.style.FadeDialogAnim

    /*** 绑定Dialog ***/
    override fun bindDialog(dialog: Dialog) {}

    /*** 设置窗体配置 ***/
    override fun bindParams(params: WindowManager.LayoutParams) {
        params.gravity = Gravity.CENTER
        params.width = dimen(R.dimen.dp280).toInt()
    }

    /*** 设置子控件 ***/
    override fun bindView(binding: DialogTipBinding) {
        binding.apply {
            mTvTitle.text = title
            mTvMessage.text = message
            mTvPositive.text = positive
            mTvNegative.text = negative
            mTvPositive.onClick {
                onPositive.invoke()
                dismiss()
            }
            mTvNegative.onClick {
                onNegative.invoke()
                dismiss()
            }
        }
    }

    fun setOnTipClickListener(onPositive: () -> Unit, onNegative: () -> Unit = {}) {
        this.onPositive = onPositive
        this.onNegative = onNegative
    }

}