package com.module.mine.ui.activity.collection.article

import androidx.recyclerview.widget.LinearLayoutManager
import com.base.base.entity.remote.Article
import com.base.base.entity.remote.PageList
import com.base.base.manager.BusManager
import com.base.base.ui.BaseVmActivity
import com.base.base.utils.onItemChildClick
import com.base.base.utils.showEmpty
import com.business.common.databinding.LayoutTitleRefreshListBinding
import com.business.common.ui.activity.web.WebActivity
import com.business.common.ui.adapter.ArticleAdapter
import com.frame.core.utils.extra.finish
import com.frame.core.utils.extra.observe
import com.frame.core.utils.extra.onRefreshAndLoadMore
import com.module.mine.R
import com.business.common.ui.dialog.DialogHelper
import com.module.mine.viewmodel.CollectArticleViewModel

/**
 * title:我的收藏
 * describe:
 *
 * @author memo
 * @date 2021-02-07 09:52
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class CollectArticleActivity : BaseVmActivity<CollectArticleViewModel, LayoutTitleRefreshListBinding>() {

    private var page = 0
    private val mAdapter by lazy { ArticleAdapter().apply { enableSwipe = true } }

    override fun bindLayoutRes(): Int = R.layout.layout_title_refresh_list

    override fun initData() {}

    override fun initView() {
        mBinding.run {
            mTitleView.setTitle("我的收藏")
            mTitleView.setRightDrawable(R.drawable.ic_collect_add)

            mRvList.run {
                layoutManager = LinearLayoutManager(mContext)
                adapter = mAdapter
            }
        }
    }

    override fun initListener() {
        // 取消收藏
        mAdapter.onItemChildClick { viewId, data ->
            when (viewId) {
                R.id.mItemDelete -> mViewModel.unCollect(data.id, data.originId)
                R.id.mItemArticle -> WebActivity.start(mContext, data.id, data.originId, data.title, data.link)
            }
        }
        // 添加站外收藏
        mBinding.mTitleView.setOnRightClickListener {
            DialogHelper.showArticleCollect(mContext) { dialog, title, author, link ->
                mViewModel.collect(dialog, title, author, link)
            }
        }
        // 刷新加载
        mBinding.mRefreshLayout.onRefreshAndLoadMore({
            page = 0
            mViewModel.getCollectionList(page)
        }, {
            mViewModel.getCollectionList(page)
        })

        // 响应收藏数据变化
        BusManager.get().collectionLiveData.observeInActivity(this) {
            // 如果不是从列表页面取消收藏的
            if (it != BusManager.COLLECTION_FROM_LIST) {
                mBinding.mRefreshLayout.autoRefresh()
            }
        }
        // 登录返回
        BusManager.get().loginLiveData.observeInActivity(mContext) { start() }

        observe(mViewModel.collectionListLiveData, this::onCollectionList)
        observe(mViewModel.unCollectLiveData, this::onUnCollection)
        observe(mViewModel.collectArticleLiveData, this::onCollectArticle)
    }

    override fun start() {
        mViewModel.getCollectionList(page)
    }

    /**
     * 获取收藏列表
     * @param data ArticleList
     */
    private fun onCollectionList(data: PageList<Article>) {
        mBinding.mRefreshLayout.finish(data.hasMore())
        if (page == 0) {
            mAdapter.setList(data.datas)
            mAdapter.showEmpty(mContext, data.isEmpty())
        } else {
            mAdapter.addData(data.datas)
        }
        if (data.hasMore()) page++
    }

    /**
     * 删除收藏文章
     * @param id Int
     */
    private fun onUnCollection(id: Int) {
        mAdapter.data.find { it.id == id }?.let { mAdapter.remove(it) }
        // 如果当前页面没有数据了 那么重新刷新列表 可能只加载一页全部删除 第二页的数据没有加载
        if (mAdapter.data.isEmpty()) mBinding.mRefreshLayout.autoRefresh()
        // 通知收藏数据变化
        BusManager.get().collectionLiveData.postValue(BusManager.COLLECTION_FROM_LIST)
    }

    /**
     * 收藏站外文章
     * @param data Any?
     */
    private fun onCollectArticle(data: Any?) {
        mBinding.mRefreshLayout.autoRefresh()
    }

}