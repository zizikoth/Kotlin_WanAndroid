package com.module.project.ui.fragment.project

import androidx.recyclerview.widget.LinearLayoutManager
import com.base.base.entity.remote.ArticleList
import com.base.base.ui.BaseVmFragment
import com.base.base.utils.onItemClickListener
import com.base.base.utils.showEmpty
import com.business.common.ui.activity.web.WebActivity
import com.business.common.ui.adapter.ArticleAdapter
import com.frame.core.utils.extra.finish
import com.frame.core.utils.extra.observe
import com.frame.core.utils.extra.onRefreshAndLoadMore
import com.frame.core.utils.extra.withArguments
import com.module.project.R
import com.module.project.databinding.FragmentProjectItemBinding
import com.module.project.viewmodel.ProjectViewModel

/**
 * title:项目类型文章列表
 * describe:
 *
 * @author memo
 * @date 2021-02-01 9:31 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class ProjectItemFragment : BaseVmFragment<ProjectViewModel, FragmentProjectItemBinding>() {
    companion object {
        fun newInstance(cid: Int) = ProjectItemFragment().withArguments("cid" to cid)
    }

    private var cid: Int = -1
    private var page: Int = 0
    private var mAdapter = ArticleAdapter()

    /*** 绑定界面 ***/
    override fun bindLayoutRes(): Int = R.layout.fragment_project_item

    override fun initData() {
        cid = arguments?.getInt("cid", cid) ?: cid
    }

    override fun initView() {
        mBinding.run {
            mRvList.run {
                layoutManager = LinearLayoutManager(mContext)
                adapter = mAdapter
            }
        }
    }

    override fun initListener() {
        // 跳转文章详情
        mAdapter.onItemClickListener { WebActivity.start(mContext, it.title, it.link) }
        // 刷新加载
        mBinding.mRefreshLayout.onRefreshAndLoadMore({
            page = 0
            mViewModel.getProjectArticles(page, cid)
        }, {
            mViewModel.getProjectArticles(page, cid)
        })
        // 接收文章
        observe(mViewModel.articleLiveData, this::onProjectArticles)
    }

    override fun start() {
        mViewModel.getProjectArticles(page, cid)
    }

    /**
     * 项目文章列表
     */
    private fun onProjectArticles(data: ArticleList) {
        mBinding.mRefreshLayout.finish(data.hasMore())
        if (page == 0) {
            mAdapter.setList(data.datas)
            mAdapter.showEmpty(mContext, data.isEmpty())
        } else {
            mAdapter.addData(data.datas)
        }
        if (data.hasMore()) page++
    }


}