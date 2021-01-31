package com.module.project.ui.fragment.project

import com.base.base.ui.BaseVmFragment
import com.business.common.indicator.init
import com.frame.core.utils.extra.paddingStatusBar
import com.module.project.R
import com.module.project.databinding.FragmentProjectBinding

/**
 * title:ProjectFragment
 * describe:
 *
 * @author memo
 * @date 2021-01-31 23:24
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class ProjectFragment : BaseVmFragment<ProjectViewModel, FragmentProjectBinding>() {
    /*** 绑定界面 ***/
    override fun bindLayoutRes(): Int = R.layout.fragment_project

    override fun initData() {
    }

    override fun initView() {
        mBinding.run {
            root.paddingStatusBar()
            mIndicator.init(mViewPager)
        }
    }

    override fun initListener() {
    }

    override fun start() {
    }


}