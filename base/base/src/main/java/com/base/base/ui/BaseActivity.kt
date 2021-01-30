package com.base.base.ui

import androidx.databinding.ViewDataBinding
import com.kongzue.dialogx.dialogs.WaitDialog
import com.frame.core.core.CoreActivity

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-12-18 5:46 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
abstract class BaseActivity<BD : ViewDataBinding> : CoreActivity<BD>() {

    fun showLoad() {
        WaitDialog.show("加载中")
    }

    fun hideLoad() {
        WaitDialog.dismiss()
    }
}