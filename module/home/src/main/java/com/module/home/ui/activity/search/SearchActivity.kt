package com.module.home.ui.activity.search

import com.base.base.ui.BaseActivity
import com.frame.core.adapter.BaseFragmentPager2Adapter
import com.frame.core.utils.extra.enableOverScrollMode
import com.module.home.R
import com.module.home.databinding.ActivitySearchBinding
import com.module.home.ui.fragment.search.SearchResultFragment
import com.module.home.ui.fragment.search.SearchWordFragment

/**
 * title:搜索页面
 * describe:
 *
 * @author memo
 * @date 2021-01-31 00:55
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class SearchActivity : BaseActivity<ActivitySearchBinding>() {

    private val mWordFragment by lazy { SearchWordFragment() }
    private val mResultFragment by lazy { SearchResultFragment() }
    private val mAdapter by lazy { BaseFragmentPager2Adapter(mContext) }

    override fun bindLayoutRes(): Int = R.layout.activity_search

    override fun initialize() {
        mBinding.run {
            // 清除搜索框
            mTitleView.setOnRightClickListener {
                mTitleView.getSearchView().setText("")
                mViewPager.setCurrentItem(0, true)
                mResultFragment.clearSearchData()
            }
            // 搜索框搜索
            mTitleView.setOnSearchListener {
                // 添加历史搜索
                mWordFragment.search(it)
                // 搜索文章列表
                mResultFragment.search(it)
                mViewPager.setCurrentItem(1, true)
            }
            // ViewPager2
            mViewPager.run {
                // 去除手指手势
                isUserInputEnabled = false
                // 去除过度拉伸阴影
                enableOverScrollMode = false
                // 预加载2页
                offscreenPageLimit = 2
                mAdapter.setData(arrayListOf(mWordFragment, mResultFragment))
                adapter = mAdapter
            }
        }
    }

    /**
     * 点击历史记录或者热门搜索 进行搜索文章
     */
    fun search(word: String) {
        mBinding.mTitleView.setEditText(word)
        mBinding.mViewPager.setCurrentItem(1, true)
        mResultFragment.search(word)
    }

    /**
     * 点击返回
     */
    override fun onBackPressed() {
        if (mBinding.mViewPager.currentItem != 0) {
            mBinding.mTitleView.getSearchView().setText("")
            mBinding.mViewPager.setCurrentItem(0, true)
            mResultFragment.clearSearchData()
        } else {
            super.onBackPressed()
        }
    }
}