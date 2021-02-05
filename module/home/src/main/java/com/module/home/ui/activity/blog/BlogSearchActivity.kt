package com.module.home.ui.activity.blog

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.base.base.entity.remote.ArticleList
import com.base.base.ui.BaseVmActivity
import com.base.base.utils.onItemClickListener
import com.base.base.utils.showEmpty
import com.business.common.databinding.LayoutTitleRefreshListBinding
import com.business.common.ui.activity.web.WebActivity
import com.business.common.ui.adapter.ArticleAdapter
import com.frame.core.utils.extra.finish
import com.frame.core.utils.extra.observe
import com.frame.core.utils.extra.onRefreshAndLoadMore
import com.frame.core.utils.extra.startActivity
import com.module.home.R
import com.module.home.databinding.ActivityBlogSearchBinding
import com.module.home.databinding.ActivityBlogSearchBindingImpl
import com.module.home.viewmodel.BlogViewModel

/**
 * title:博客内容搜索
 * describe:
 *
 * @author memo
 * @date 2021-02-01 11:38
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class BlogSearchActivity : BaseVmActivity<BlogViewModel, ActivityBlogSearchBinding>() {

    companion object {
        fun start(context: Context, cid: Int) {
            context.startActivity<BlogSearchActivity>("cid" to cid)
        }
    }

    private var page = 0
    private var cid: Int = -1
    private var word: String = ""
    private val mAdapter = ArticleAdapter()

    override fun bindLayoutRes(): Int = R.layout.activity_blog_search

    override fun initData() {
        cid = intent.getIntExtra("cid", cid)
    }

    override fun initView() {
        showContent()
        mBinding.mTitleView.run {
        }
        mBinding.mRvList.run {
            layoutManager = LinearLayoutManager(mContext)
            adapter = mAdapter
        }
    }

    override fun initListener() {
        // 文章搜索
        mBinding.mTitleView.setOnSearchListener {
            page = 0
            word = it
            mViewModel.getBlogArticles(page, cid, word, true)
        }
        // 跳转文章详情
        mAdapter.onItemClickListener { WebActivity.start(mContext, it.title, it.link) }
        // 刷新加载
        mBinding.mRefreshLayout.onRefreshAndLoadMore({
            page = 0
            mViewModel.getBlogArticles(page, cid, word)
        }, {
            mViewModel.getBlogArticles(page, cid, word)
        })
        // 接收文章
        observe(mViewModel.articleLiveData, this::onBlogArticles)
    }

    override fun start() {}

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