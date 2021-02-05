package com.module.project.ui.fragment.project

import com.alibaba.android.arouter.facade.annotation.Route
import com.base.base.entity.remote.ArticleTree
import com.base.base.manager.RouterManager
import com.base.base.ui.BaseVmFragment
import com.business.common.indicator.IndicatorType
import com.business.common.indicator.init
import com.business.common.indicator.setTitles
import com.frame.core.utils.extra.enableOverScrollMode
import com.frame.core.utils.extra.observe
import com.frame.core.utils.extra.paddingStatusBar
import com.module.project.R
import com.module.project.databinding.FragmentProjectBinding
import com.module.project.ui.adapter.ProjectFragmentAdapter
import com.module.project.viewmodel.ProjectViewModel

/**
 * title:项目类型
 * describe:
 *
 * @author memo
 * @date 2021-01-31 23:24
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
@Route(path = RouterManager.Project.PROJECT_FRAGMENT)
class ProjectFragment : BaseVmFragment<ProjectViewModel, FragmentProjectBinding>() {

    private val mAdapter by lazy { ProjectFragmentAdapter(this) }

    /*** 绑定界面 ***/
    override fun bindLayoutRes(): Int = R.layout.fragment_project

    override fun initData() {
    }

    override fun initView() {
        mBinding.run {
            root.paddingStatusBar()
            // 配置Tab
            mBinding.mIndicator.init(mViewPager, IndicatorType.ElasticLine)
            // 去除过度拉伸
            mViewPager.run {
                enableOverScrollMode = false
                offscreenPageLimit = 7
                adapter = mAdapter
            }
        }
    }

    override fun initListener() {
        observe(mViewModel.treeLiveData, this::onProjectTree)
    }

    override fun start() {
        mViewModel.getProjectTree()
    }

    /**
     * 获取项目类型
     * @param data 项目类型
     */
    private fun onProjectTree(data: ArrayList<ArticleTree>) {
        mAdapter.setIds(data.map { it.id })
        mBinding.mIndicator.setTitles(data.map { it.name }, IndicatorType.ElasticLine)
    }

}