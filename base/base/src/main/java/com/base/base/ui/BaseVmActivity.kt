package com.base.base.ui

import androidx.databinding.ViewDataBinding
import com.base.base.dialog.LoadingDialog
import com.base.base.http.ApiCode
import com.base.base.ui.mvvm.BaseViewModel
import com.base.base.ui.status.NetErrorCallback
import com.base.base.ui.status.ServerErrorCallback
import com.frame.core.core.CoreVmActivity
import com.load.status.core.LoadService
import com.load.status.core.LoadStatus

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-12-18 10:48 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
abstract class BaseVmActivity<VM : BaseViewModel, BD : ViewDataBinding> : CoreVmActivity<VM, BD>() {

    private val mLoadDialog by lazy { LoadingDialog() }

    protected var mLoadService: LoadService<*>? = null

    override fun doOnBefore() {
        super.doOnBefore()
        mLoadService = LoadStatus.getDefault().register(mBinding.root) {
            showLoad()
            start()
        }
        // 加载框
        mViewModel.loadingEvent.observe(this) { if (it) showLoad() else hideLoad() }
        // 加载页面
        mViewModel.statusEvent.observe(this) {
            if (it.isFirstLoad) {
                when (it.code) {
                    ApiCode.ServerError -> mLoadService?.showCallback(ServerErrorCallback::class.java)
                    ApiCode.NetError -> mLoadService?.showCallback(NetErrorCallback::class.java)
                }
            } else {
                mLoadService?.showSuccess()
            }
        }
    }

    fun showLoad() {
        if(!mLoadDialog.isVisible) {
            mLoadDialog.show(supportFragmentManager)
        }
    }

    fun hideLoad() {
        if (mLoadDialog.isVisible) {
            mLoadDialog.dismiss()
        }
    }
}