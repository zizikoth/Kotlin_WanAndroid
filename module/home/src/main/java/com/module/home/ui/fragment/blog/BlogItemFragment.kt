package com.module.home.ui.fragment.blog

import androidx.recyclerview.widget.LinearLayoutManager
import com.base.base.entity.remote.ArticleList
import com.base.base.ui.BaseVmFragment
import com.base.base.utils.onItemChildClick
import com.base.base.utils.showEmpty
import com.business.common.ui.activity.web.WebActivity
import com.business.common.ui.adapter.ArticleAdapter
import com.frame.core.utils.extra.finish
import com.frame.core.utils.extra.observe
import com.frame.core.utils.extra.onRefreshAndLoadMore
import com.frame.core.utils.extra.withArguments
import com.module.home.R
import com.module.home.databinding.FragmentBlogItemBinding
import com.module.home.viewmodel.BlogViewModel

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-01 11:03 AM
 * @email zhou_android@163.com
 *
 *
 * Talk is cheap, Show me the code.
 */
class BlogItemFragment : BaseVmFragment<BlogViewModel, FragmentBlogItemBinding>() {

    companion object {
        fun newInstance(cid: Int) = BlogItemFragment().withArguments("cid" to cid)
    }

    private val mAdapter = ArticleAdapter()

    private var cid: Int = -1
    private var page = 0

    /*** 绑定界面 ***/
    override fun bindLayoutRes(): Int = R.layout.fragment_blog_item

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
        mAdapter.onItemChildClick { viewId, data ->
            if (viewId == R.id.mItemArticle) WebActivity.start(mContext, data.id, data.title, data.link)
        }

        // 刷新加载
        mBinding.mRefreshLayout.onRefreshAndLoadMore({
            page = 0
            mViewModel.getBlogArticles(page, cid)
        }, {
            mViewModel.getBlogArticles(page, cid)
        })
        // 接收文章
        observe(mViewModel.articleLiveData, this::onBlogArticles)
    }

    override fun start() {
        mViewModel.getBlogArticles(page, cid)
    }

    /**
     * 获取博客文章列表
     */
    private fun onBlogArticles(data: ArticleList) {
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