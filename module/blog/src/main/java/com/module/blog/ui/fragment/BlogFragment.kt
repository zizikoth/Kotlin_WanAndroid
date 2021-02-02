package com.module.blog.ui.fragment

import com.base.base.entity.remote.ArticleTree
import com.base.base.ui.BaseVmFragment
import com.business.common.indicator.IndicatorType
import com.business.common.indicator.init
import com.business.common.indicator.setTitles
import com.frame.core.utils.extra.*
import com.module.blog.R
import com.module.blog.databinding.FragmentBlogBinding
import com.module.blog.ui.activity.BlogSearchActivity
import com.module.blog.ui.adapter.BlogFragmentAdapter

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-01 11:03 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class BlogFragment : BaseVmFragment<BlogViewModel, FragmentBlogBinding>() {

    private var ids = arrayListOf<Int>()
    private val mAdapter by lazy { BlogFragmentAdapter(this) }

    /*** 绑定界面 ***/
    override fun bindLayoutRes(): Int = R.layout.fragment_blog

    override fun initData() {}

    override fun initView() {
        mBinding.run {
            root.paddingStatusBar()
            // 配置Tab
            mIndicator.init(mBinding.mViewPager, IndicatorType.ElasticLine)
            // 去除过度拉伸
            mViewPager.run {
                enableOverScrollMode = false
                offscreenPageLimit = 7
                adapter = mAdapter
            }
            // 文章搜索
            mBtnSearch.onClick { BlogSearchActivity.start(mContext, ids[mViewPager.currentItem]) }
        }
    }

    override fun initListener() {
        observe(mViewModel.blogLiveData, this::onBlogTree)
    }

    override fun start() {
        mViewModel.getBlogTree()
    }


    private fun onBlogTree(data: ArrayList<ArticleTree>) {
        ids = data.map { it.id }.toArrayList()
        mAdapter.setIds(ids)
        mBinding.mIndicator.setTitles(data.map { it.name }, IndicatorType.ElasticLine)
    }

}