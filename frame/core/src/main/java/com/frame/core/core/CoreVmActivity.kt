package com.frame.core.core

import android.content.Intent
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * title:CoreVmActivity
 * describe:
 *
 * @author memo
 * @date 2020-12-18 10:31 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
abstract class CoreVmActivity<VM : ViewModel, BD : ViewDataBinding> : CoreActivity<BD>() {

    protected lateinit var mViewModel: VM

    override fun doOnBefore() {
        super.doOnBefore()
        mViewModel = ViewModelProvider(this).get(getViewModelClass(this) as Class<VM>)
    }

    override fun initialize() {
        initData(intent)
        initView()
        initListener()
        start()
    }

    protected abstract fun initData(intent: Intent)
    protected abstract fun initView()
    protected abstract fun initListener()
    protected abstract fun start()
}