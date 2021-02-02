package com.module.home.ui.fragment.search

import com.base.base.ui.BaseVmFragment
import com.base.base.utils.onItemClickListener
import com.frame.core.utils.extra.*
import com.google.android.flexbox.FlexboxLayoutManager
import com.module.home.R
import com.module.home.databinding.FragmentSearchWordBinding
import com.module.home.ui.activity.search.SearchActivity
import com.module.home.ui.adapter.SearchAdapter

/**
 * title:搜索 - 历史记录 热门搜索
 * describe:
 *
 * @author memo
 * @date 2021-01-31 11:01
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class SearchWordFragment : BaseVmFragment<SearchViewModel, FragmentSearchWordBinding>() {

    private val mHistoryAdapter = SearchAdapter()
    private val mHotAdapter = SearchAdapter()

    /*** 绑定界面 ***/
    override fun bindLayoutRes(): Int = R.layout.fragment_search_word

    override fun initData() {}

    override fun initView() {
        mBinding.run {
            mRvHistory.run {
                layoutManager = FlexboxLayoutManager(mContext)
                adapter = mHistoryAdapter
            }
            mRvHot.run {
                layoutManager = FlexboxLayoutManager(mContext)
                adapter = mHotAdapter
            }
        }
        showContent()
    }

    override fun initListener() {
        // 清空历史记录
        mBinding.mTvHistory.onClick {
            mViewModel.clearSearch()
            mBinding.mTvHistory.gone()
            mBinding.mRvHistory.gone()
            mHistoryAdapter.setList(listOf())
        }
        // 点击历史条目
        mHistoryAdapter.onItemClickListener {
            (mContext as SearchActivity).search(it)
        }
        // 点击热门条目
        mHotAdapter.onItemClickListener {
            search(it)
            (mContext as SearchActivity).search(it)
        }

        // 获取历史搜索
        observe(mViewModel.historyWordLiveData, this::onHistoryWords)
        // 获取搜索热词
        observe(mViewModel.hotWordLiveData, this::onHotWords)
    }

    override fun start() {
        mViewModel.getAllWord()
    }

    /**
     * 搜索界面进行搜索
     */
    fun search(word: String) {
        mViewModel.addSearch(word)
        if (!mHistoryAdapter.data.contains(word)) {
            if (mHistoryAdapter.data.size < 20) {
                mHistoryAdapter.addData(0, word)
            } else {
                val data = mHistoryAdapter.data
                data.add(0, word)
                data.removeLast()
                mHistoryAdapter.setList(data)
            }
        }
        mBinding.mTvHistory.visible()
        mBinding.mRvHistory.visible()
    }

    /**
     * 获取到搜索历史记录
     */
    private fun onHistoryWords(data: ArrayList<String>) {
        // 历史记录
        val showHistory = data.isNotEmpty()
        mBinding.mTvHistory.setVisible(showHistory)
        mBinding.mRvHistory.setVisible(showHistory)
        mHistoryAdapter.setList(data)
    }

    /**
     * 获取搜索热词
     */
    private fun onHotWords(data: ArrayList<String>) {
        // 搜索热词
        val showHot = data.isNotEmpty()
        mBinding.mTvHot.setVisible(showHot)
        mBinding.mRvHot.setVisible(showHot)
        mHotAdapter.setList(data)
    }

}