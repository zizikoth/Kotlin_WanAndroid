package com.base.base.ui

import androidx.databinding.ViewDataBinding
import com.frame.core.core.CoreActivity
import com.kongzue.dialog.v3.WaitDialog

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
        WaitDialog.show(mContext, "加载中")
    }

    fun hideLoad() {
        WaitDialog.dismiss()
    }
}