package com.module.system.ui.fragment

import com.base.base.ui.BaseFragment
import com.business.common.indicator.IndicatorType
import com.business.common.indicator.init
import com.business.common.indicator.setTitles
import com.frame.core.utils.extra.enableOverScrollMode
import com.frame.core.utils.extra.paddingStatusBar
import com.module.system.R
import com.module.system.databinding.FragmentSystemBinding
import com.module.system.ui.adapter.SystemFragmentAdapter

/**
 * title:系统页面
 * describe:
 *
 * @author memo
 * @date 2021-02-01 16:21
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class SystemFragment : BaseFragment<FragmentSystemBinding>() {

    private val titles = listOf("体系", "导航")
    private val mAdapter by lazy { SystemFragmentAdapter(this) }

    /*** 绑定界面 ***/
    override fun bindLayoutRes(): Int = R.layout.fragment_system

    /*** 初始化 ***/
    override fun initialize() {
        mBinding.run {
            root.paddingStatusBar()
            // 设置Tab
            mIndicator.init(mViewPager, IndicatorType.RoundCover)
            mIndicator.setTitles(titles, IndicatorType.RoundCover)
            // 去除过度拉伸
            mViewPager.run {
                enableOverScrollMode = false
                offscreenPageLimit = 2
                adapter = mAdapter
            }
        }
    }
}