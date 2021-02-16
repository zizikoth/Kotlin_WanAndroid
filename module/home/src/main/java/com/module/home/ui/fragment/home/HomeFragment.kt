package com.module.home.ui.fragment.home

import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.base.base.entity.remote.*
import com.base.base.manager.RouterManager
import com.base.base.ui.BaseVmFragment
import com.base.base.utils.onMultiItemClick
import com.base.base.utils.showEmpty
import com.business.common.ui.activity.web.WebActivity
import com.business.common.ui.adapter.ArticleAdapter
import com.frame.core.utils.extra.*
import com.google.android.material.appbar.AppBarLayout
import com.module.home.R
import com.module.home.databinding.FragmentHomeBinding
import com.module.home.ui.activity.article.NewArticleActivity
import com.module.home.ui.activity.blog.BlogActivity
import com.module.home.ui.activity.search.SearchActivity
import com.module.home.ui.adapter.BannerAdapter
import com.module.home.viewmodel.HomeViewModel
import java.util.*
import kotlin.math.abs

/**
 * title:首页
 * describe:
 *
 * @author memo
 * @date 2021-01-30 15:49
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
@Route(path = RouterManager.Home.HOME_FRAGMENT)
class HomeFragment : BaseVmFragment<HomeViewModel, FragmentHomeBinding>() {

    /*** 页码 ***/
    private var page = 0

    private val mAdapter = ArticleAdapter()

    /*** 绑定界面 ***/
    override fun bindLayoutRes(): Int = R.layout.fragment_home

    override fun initData() {}

    override fun initView() {
        mBinding.run {
            mToolBar.paddingStatusBar()
            mFlSearch.marginStatusBar()
            mBanner.setLifecycleRegistry(this@HomeFragment.lifecycle).setAdapter(BannerAdapter())
            mRvList.run {
                layoutManager = GridLayoutManager(mContext, 4)
                mAdapter.setGridSpanSizeLookup { _, viewType, position ->
                    when (viewType) {
                        HOME_TYPE_TITLE, HOME_TYPE_NORMAL_ARTICLE, HOME_TYPE_NEW_ARTICLE -> 4
                        HOME_TYPE_SYSTEM_GRID -> 1
                        else -> 4
                    }
                }
                adapter = mAdapter
            }
        }
    }

    override fun initListener() {
        mBinding.run {
            // 设置AppBar的透明度
            mAppBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBar, offset ->
                mToolBar.alpha = abs(offset).toFloat() / appBar.totalScrollRange
            })
            // 标题栏点击标题滑动到顶部
            mTitleView.setOnTitleClickListener {
                // 但顶部完全显示 滑动到顶部
                if (mToolBar.alpha == 1f) {
                    val behavior = (mAppBar.layoutParams as CoordinatorLayout.LayoutParams).behavior
                    if (behavior is AppBarLayout.Behavior) {
                        behavior.topAndBottomOffset = 0
                    }
                    mRvList.scrollToPosition(0)
                }
            }
            // 刷新 加载 监听
            mRefreshLayout.onRefreshAndLoadMore({
                page = 0
                mViewModel.getHomeData()
            }, {
                mViewModel.getArticles(page)
            })
            // 搜索
            mFlSearch.onClick {
                startActivity<SearchActivity>()
            }
            // 轮播图
            mBanner.setOnPageClickListener { _, position ->
                (mBanner.data[position] as HomeBanner).let { WebActivity.start(mContext, it.id, it.title, it.url) }
            }
            // 列表
            mAdapter.onMultiItemClick { multiType, data ->
                when (multiType) {
                    // 标题
                    HOME_TYPE_TITLE -> if (data.hasMore) startActivity<NewArticleActivity>()
                    // 普通文章
                    HOME_TYPE_NORMAL_ARTICLE -> WebActivity.start(mContext, data.id, data.title, data.link)
                    // 博客
                    HOME_TYPE_SYSTEM_GRID -> BlogActivity.start(mContext, data.id)
                }
            }
            // 最新文章
            mAdapter.onNewArticleClick = { WebActivity.start(mContext, it.id, it.title, it.link) }
        }
        //网络数据回调
        observe(mViewModel.bannerLiveData, this::onBanner)
        observe(mViewModel.articleLiveData, this::onArticle)
    }

    override fun start() {
        mViewModel.getHomeData()
    }

    /**
     * Banner回调
     */
    private fun onBanner(data: ArrayList<HomeBanner>) {
        mBinding.mBanner.create(data)
    }

    /**
     * 文章列表回调
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