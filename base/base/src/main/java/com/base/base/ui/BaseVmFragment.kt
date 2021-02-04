package com.base.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.base.base.api.ApiCode
import com.base.base.ui.mvvm.BaseViewModel
import com.base.base.ui.status.NetErrorCallback
import com.base.base.ui.status.ServerErrorCallback
import com.frame.core.core.getViewModelClass
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
abstract class BaseVmFragment<VM : BaseViewModel, BD : ViewDataBinding> : BaseFragment<BD>() {

    protected lateinit var mViewModel: VM

    protected var mLoadService: LoadService<*>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, bindLayoutRes(), container, false)
        mBinding.lifecycleOwner = this
        mLoadService = LoadStatus.getDefault().register(mBinding.root) {
            showLoad()
            start()
        }
        return mLoadService?.loadLayout
    }

    override fun doOnBefore() {
        super.doOnBefore()
        mViewModel = ViewModelProvider(this).get(getViewModelClass(this) as Class<VM>)
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

    override fun initialize() {
        initData()
        initView()
        initListener()
        start()
    }

    abstract fun initData()
    abstract fun initView()
    abstract fun initListener()
    abstract fun start()

    protected fun showContent() = mLoadService?.showSuccess()

    protected fun showLoad() {
        if (mContext is BaseActivity<*>) {
            (mContext as BaseActivity<*>).showLoad()
        }
    }

    protected fun hideLoad() {
        if (mContext is BaseActivity<*>) {
            (mContext as BaseActivity<*>).hideLoad()
        }
    }
}