package com.base.base.ui

import androidx.databinding.ViewDataBinding
import com.base.base.dialog.LoadingDialog
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

    private val mLoadDialog by lazy { LoadingDialog() }

    fun showLoad() {
        if (!mLoadDialog.isVisible) {
            mLoadDialog.show(supportFragmentManager)
        }
    }

    fun hideLoad() {
        if (mLoadDialog.isVisible) {
            mLoadDialog.dismiss()
        }
    }
}