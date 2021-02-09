package com.module.home.ui.fragment.search

import androidx.recyclerview.widget.LinearLayoutManager
import com.base.base.entity.remote.Article
import com.base.base.entity.remote.PageList
import com.base.base.ui.BaseVmFragment
import com.base.base.utils.onItemChildClick
import com.base.base.utils.showEmpty
import com.business.common.ui.activity.web.WebActivity
import com.business.common.ui.adapter.ArticleAdapter
import com.frame.core.utils.extra.finish
import com.frame.core.utils.extra.observe
import com.frame.core.utils.extra.onRefreshAndLoadMore
import com.module.home.R
import com.module.home.databinding.FragmentSearchResultBinding
import com.module.home.viewmodel.SearchViewModel

/**
 * title:搜索 - 搜索文章列表
 * describe:
 *
 * @author memo
 * @date 2021-01-31 11:02
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class SearchResultFragment : BaseVmFragment<SearchViewModel, FragmentSearchResultBinding>() {

    private var page = 0
    private var word = ""
    private val mAdapter by lazy { ArticleAdapter() }

    /*** 绑定界面 ***/
    override fun bindLayoutRes(): Int = R.layout.fragment_search_result

    override fun initData() {}

    override fun initView() {
        mBinding.mRvList.run {
            layoutManager = LinearLayoutManager(mContext)
            adapter = mAdapter
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
            mViewModel.getArticleByWord(word, page)
        }, {
            mViewModel.getArticleByWord(word, page)
        })
        // 文章搜索结果
        observe(mViewModel.articleLiveData, this::onArticle)
    }

    override fun start() {}

    /**
     * 主页点击搜索进行搜索文章
     */
    fun search(word: String) {
        page = 0
        this.word = word
        mViewModel.getArticleByWord(word, page)
    }

    /**
     * 主页清除搜索内容
     */
    fun clearSearchData() {
        mAdapter.setList(listOf())
    }

    /**
     * 搜索文章回调
     */
    private fun onArticle(data: PageList<Article>) {
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