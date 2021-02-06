package com.module.home.ui.activity.blog

import android.content.Context
import com.base.base.entity.remote.ArticleTree
import com.base.base.ui.BaseVmActivity
import com.base.base.utils.indicator.IndicatorType
import com.base.base.utils.indicator.init
import com.base.base.utils.indicator.setTitles
import com.frame.core.utils.extra.enableOverScrollMode
import com.frame.core.utils.extra.observe
import com.frame.core.utils.extra.startActivity
import com.frame.core.utils.extra.toArrayList
import com.module.home.R
import com.module.home.databinding.ActivityBlogBinding
import com.module.home.ui.adapter.BlogFragmentAdapter
import com.module.home.viewmodel.BlogViewModel

class BlogActivity : BaseVmActivity<BlogViewModel, ActivityBlogBinding>() {

    companion object {
        fun start(context: Context, id: Int) {
            context.startActivity<BlogActivity>("id" to id)
        }
    }

    private var id = 9527
    private var ids = arrayListOf<Int>()
    private val mAdapter by lazy { BlogFragmentAdapter(this) }

    override fun bindLayoutRes(): Int = R.layout.activity_blog

    override fun initData() {
        id = intent.getIntExtra("id", id)
    }

    override fun initView() {
        mBinding.run {
            // 配置Tab
            mIndicator.init(mBinding.mViewPager, IndicatorType.ElasticLine)
            // 去除过度拉伸
            mViewPager.run {
                enableOverScrollMode = false
                offscreenPageLimit = 7
                adapter = mAdapter
            }
            // 文章搜索
            mTitleView.setOnRightClickListener { BlogSearchActivity.start(mContext, ids[mViewPager.currentItem]) }
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
        val index = ids.indexOf(id)
        mBinding.mViewPager.currentItem = if (index == -1) 0 else index
    }
}