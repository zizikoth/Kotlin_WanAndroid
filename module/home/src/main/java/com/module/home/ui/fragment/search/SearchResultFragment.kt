package com.module.home.ui.fragment.search

import androidx.recyclerview.widget.LinearLayoutManager
import com.base.base.entity.remote.ArticleList
import com.base.base.ui.BaseVmFragment
import com.business.common.ui.activity.web.WebActivity
import com.frame.core.utils.extra.finish
import com.frame.core.utils.extra.observe
import com.frame.core.utils.extra.onRefreshAndLoadMore
import com.module.home.R
import com.module.home.databinding.FragmentSearchResultBinding
import com.module.home.ui.adapter.ArticleAdapter

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
        // 条目点击
        mAdapter.setOnItemClickListener { _, _, position ->
            mAdapter.getItem(position).let { WebActivity.start(mContext, it.title, it.link) }
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
    private fun onArticle(data: ArticleList) {
        if (data.hasMore()) page++
        mBinding.mRefreshLayout.finish(data.hasMore())
        if (data.isFirst()) {
            mAdapter.setList(data.datas)
        } else {
            mAdapter.addData(data.datas)
        }
    }


}