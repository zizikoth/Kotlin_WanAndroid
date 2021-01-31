package com.module.home.ui.fragment.home

import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.base.base.entity.remote.ArticleList
import com.base.base.entity.remote.HomeBanner
import com.base.base.ui.BaseVmFragment
import com.business.common.ui.activity.web.WebActivity
import com.frame.core.utils.extra.*
import com.google.android.material.appbar.AppBarLayout
import com.module.home.R
import com.module.home.databinding.FragmentHomeBinding
import com.module.home.ui.activity.search.SearchActivity
import com.module.home.ui.adapter.ArticleAdapter
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.CircleIndicator
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
class HomeFragment : BaseVmFragment<HomeViewModel, FragmentHomeBinding>() {

    /*** 页码 ***/
    private var page = 0

    private val mAdapter = ArticleAdapter()

    private val mBannerAdapter = object : BannerImageAdapter<HomeBanner>(null) {
        override fun onBindView(holder: BannerImageHolder, data: HomeBanner, position: Int, size: Int) {
            holder.imageView.load(data.imagePath)
        }
    }


    /*** 绑定界面 ***/
    override fun bindLayoutRes(): Int = R.layout.fragment_home

    override fun initData() {}

    override fun initView() {
        mBinding.run {
            mToolBar.paddingStatusBar()
            mFlSearch.marginStatusBar()
            mBanner.setAdapter(mBannerAdapter)
                .setIndicator(CircleIndicator(mContext))
                .addBannerLifecycleObserver(mContext)
            mRvList.run {
                layoutManager = LinearLayoutManager(mContext)
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
                mViewModel.getArticles(page)
            }, {
                mViewModel.getArticles(page)
            })
            // 搜索
            mFlSearch.onClick {
                startActivity<SearchActivity>()
            }
            // 轮播图
            mBanner.setOnBannerListener { data, _ ->
                (data as HomeBanner?)?.let { WebActivity.start(mContext, it.title, it.url) }
            }

            // 列表
            mAdapter.setOnItemClickListener { _, _, position ->
                mAdapter.getItem(position).let { WebActivity.start(mContext, it.title, it.link) }
            }
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
        mBannerAdapter.setDatas(data)
        mBannerAdapter.notifyDataSetChanged()
    }

    /**
     * 文章列表回调
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