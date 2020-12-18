package com.memo.core

import android.os.Bundle
import com.base.base.ui.BaseVmFragment
import com.memo.core.databinding.FragmentMainBinding

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-12-19 3:02 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class MainFragment : BaseVmFragment<MainViewModel, FragmentMainBinding>() {

    /*** 绑定界面 ***/
    override fun bindLayoutRes(): Int = R.layout.fragment_main

    override fun initData(arguments: Bundle?) {
    }

    override fun initView() {
    }

    override fun initListener() {
    }

    override fun start() {
        mViewModel.getBanner()
    }


}