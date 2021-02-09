package com.frame.core.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.LogUtils

/**
 * title:CoreFragment
 * describe:
 *
 * @author memo
 * @date 2020-12-18 10:31 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
abstract class CoreFragment<BD : ViewDataBinding> : Fragment() {

    protected val mContext: AppCompatActivity by lazy { requireActivity() as AppCompatActivity }

    protected lateinit var mBinding: BD

    /*** 标识 标识是否界面准备完毕 ***/
    private var isPrepared: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, bindLayoutRes(), container, false)
        mBinding.lifecycleOwner = this
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isPrepared = true
        doOnBefore()
        onVisibleToUser()
    }

    private fun onVisibleToUser() {
        if (isPrepared && isResumed) {
            isPrepared = false
            initialize()
        }
    }

    override fun onResume() {
        super.onResume()
        if (isPrepared) onVisibleToUser()
        LogUtils.iTag("TopPage", this::class.java.simpleName)
    }

    protected open fun doOnBefore() {}

    /*** 绑定界面 ***/
    @LayoutRes
    protected abstract fun bindLayoutRes(): Int

    /*** 初始化 ***/
    protected abstract fun initialize()
}