package com.module.mine.ui.activity.collection

import androidx.recyclerview.widget.LinearLayoutManager
import com.base.base.entity.remote.ArticleList
import com.base.base.manager.BusViewModel
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
import com.module.mine.viewmodel.CollectionViewModel

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
class CollectionActivity : BaseVmActivity<CollectionViewModel, LayoutTitleRefreshListBinding>() {

    private var page = 0
    private val mAdapter by lazy { ArticleAdapter().apply { enableSwipe = true } }

    override fun bindLayoutRes(): Int = R.layout.layout_title_refresh_list

    override fun initData() {}

    override fun initView() {
        mBinding.run {
            mTitleView.setTitle("我的收藏")

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
        // 刷新加载
        mBinding.mRefreshLayout.onRefreshAndLoadMore({
            page = 0
            mViewModel.getCollectionList(page)
        }, {
            mViewModel.getCollectionList(page)
        })

        // 响应收藏数据变化
        BusViewModel.get().collectionLiveData.observeInActivity(this) { mBinding.mRefreshLayout.autoRefresh() }

        observe(mViewModel.collectionsLiveData, this::onCollectionList)
        observe(mViewModel.unCollectionLiveData, this::onUnCollection)
    }

    override fun start() {
        mViewModel.getCollectionList(page)
    }

    /**
     * 获取收藏列表
     * @param data ArticleList
     */
    private fun onCollectionList(data: ArticleList) {
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
        BusViewModel.get().collectionLiveData.postValue("")
    }

}