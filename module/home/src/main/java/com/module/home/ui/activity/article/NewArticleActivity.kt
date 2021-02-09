package com.module.home.ui.activity.article

import androidx.recyclerview.widget.LinearLayoutManager
import com.base.base.entity.remote.Article
import com.base.base.entity.remote.PageList
import com.base.base.ui.BaseVmActivity
import com.base.base.utils.onItemChildClick
import com.base.base.utils.showEmpty
import com.business.common.databinding.LayoutTitleRefreshListBinding
import com.business.common.ui.activity.web.WebActivity
import com.business.common.ui.adapter.ArticleAdapter
import com.frame.core.utils.extra.finish
import com.frame.core.utils.extra.observe
import com.frame.core.utils.extra.onRefreshAndLoadMore
import com.module.home.R
import com.module.home.viewmodel.NewArticleViewModel

/**
 * title:最新文章页面
 * describe:
 *
 * @author memo
 * @date 2021-02-06 00:01
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class NewArticleActivity : BaseVmActivity<NewArticleViewModel, LayoutTitleRefreshListBinding>() {

    private var page = 0
    private val mAdapter = ArticleAdapter()

    override fun bindLayoutRes(): Int = R.layout.layout_title_refresh_list

    override fun initData() {}

    override fun initView() {
        mBinding.run {
            mTitleView.setTitle("最新项目")
            mRvList.run {
                layoutManager = LinearLayoutManager(mContext)
                adapter = mAdapter
            }
        }
    }

    override fun initListener() {
        // 列表
        mAdapter.onItemChildClick { viewId, data ->
            if (viewId == R.id.mItemArticle) WebActivity.start(mContext, data.id, data.title, data.link)
        }

        mBinding.mRefreshLayout.onRefreshAndLoadMore({
            page = 0
            mViewModel.getNewArticle(page)
        }, {
            mViewModel.getNewArticle(page)
        })
        observe(mViewModel.articleLiveData, this::onArticles)

    }

    override fun start() {
        mViewModel.getNewArticle(page)
    }

    private fun onArticles(data: PageList<Article>) {
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