package com.base.base.ui

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
 * @date 2020-12-18 10:48 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
abstract class BaseVmActivity<VM : BaseViewModel, BD : ViewDataBinding> : BaseActivity<BD>() {

    protected lateinit var mViewModel:VM

    protected var mLoadService: LoadService<*>? = null

    override fun doOnBefore() {
        super.doOnBefore()
        mViewModel = ViewModelProvider(this).get(getViewModelClass(this) as Class<VM>)
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

}