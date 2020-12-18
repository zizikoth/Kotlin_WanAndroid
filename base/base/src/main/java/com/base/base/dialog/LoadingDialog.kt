package com.base.base.dialog

import android.app.Dialog
import android.view.Gravity
import android.view.WindowManager
import com.base.base.R
import com.base.base.databinding.DialogLoadBinding
import com.frame.core.dialog.BaseDialog
import com.frame.core.utils.extra.dimen

/**
 * title:加载弹窗
 * describe:
 *
 * @author memo
 * @date 2020-12-18 4:29 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class LoadingDialog : BaseDialog<DialogLoadBinding>() {

    /*** 绑定布局 ***/
    override fun bindLayoutRes(): Int = R.layout.dialog_load

    /*** 窗体动画 ***/
    override fun bindAnimStyleRes(): Int = R.style.FadeDialogAnim

    /*** 绑定Dialog ***/
    override fun bindDialog(dialog: Dialog) {}

    /*** 设置窗体配置 ***/
    override fun bindParams(params: WindowManager.LayoutParams) {
        params.gravity = Gravity.CENTER
        params.width = dimen(R.dimen.dp120).toInt()
        params.height = dimen(R.dimen.dp120).toInt()
    }

    /*** 设置子控件 ***/
    override fun bindView(binding: DialogLoadBinding) {}

}