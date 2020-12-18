package com.base.base.ui

import androidx.databinding.ViewDataBinding
import com.base.base.http.ApiCode
import com.base.base.ui.mvvm.BaseViewModel
import com.base.base.ui.status.NetErrorCallback
import com.base.base.ui.status.ServerErrorCallback
import com.frame.core.core.CoreVmFragment
import com.load.status.core.LoadService
import com.load.status.core.LoadStatus

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-12-18 11:10 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
abstract class BaseVmFragment<VM : BaseViewModel, BD : ViewDataBinding> : CoreVmFragment<VM, BD>() {

    private var mLoadService: LoadService<*>? = null

    override fun doOnBefore() {
        super.doOnBefore()
        mLoadService = LoadStatus.getDefault().register(mBinding.root) {
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

    protected fun showLoad() {
        if (mContext is BaseActivity<*>) {
            (mContext as BaseActivity<*>).showLoad()
        } else if (mContext is BaseVmActivity<*, *>) {
            (mContext as BaseVmActivity<*, *>).showLoad()
        }
    }

    protected fun hideLoad() {
        if (mContext is BaseActivity<*>) {
            (mContext as BaseActivity<*>).hideLoad()
        } else if (mContext is BaseVmActivity<*, *>) {
            (mContext as BaseVmActivity<*, *>).hideLoad()
        }
    }
}