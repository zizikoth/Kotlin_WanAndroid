package com.frame.core.core

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-12-18 10:31 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
abstract class CoreVmFragment<VM : ViewModel, BD : ViewDataBinding> : CoreFragment<BD>() {

    protected lateinit var mViewModel: VM

    override fun doOnBefore() {
        super.doOnBefore()
        mViewModel = ViewModelProvider(this).get(getViewModelClass(this) as Class<VM>)
    }

    override fun initialize() {
        initData(arguments)
        initView()
        initListener()
        start()
    }

    protected abstract fun initData(arguments: Bundle?)
    protected abstract fun initView()
    protected abstract fun initListener()
    protected abstract fun start()
}