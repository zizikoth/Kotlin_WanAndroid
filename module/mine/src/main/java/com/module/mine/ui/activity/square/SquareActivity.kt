package com.module.mine.ui.activity.square

import androidx.recyclerview.widget.LinearLayoutManager
import com.base.base.entity.remote.Article
import com.base.base.entity.remote.PageList
import com.base.base.manager.DataManager
import com.base.base.ui.BaseVmActivity
import com.base.base.utils.onItemChildClick
import com.base.base.utils.onItemChildLongClick
import com.base.base.utils.showEmpty
import com.business.common.databinding.LayoutTitleRefreshListBinding
import com.business.common.ui.activity.web.WebActivity
import com.business.common.ui.adapter.ArticleAdapter
import com.business.common.ui.dialog.DialogHelper
import com.frame.core.utils.extra.finish
import com.frame.core.utils.extra.observe
import com.frame.core.utils.extra.onRefreshAndLoadMore
import com.module.mine.R
import com.module.mine.viewmodel.SquareViewModel

/**
 * title:广场分享列表
 * describe:
 *
 * @author memo
 * @date 2021-02-10 2:19 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class SquareActivity : BaseVmActivity<SquareViewModel, LayoutTitleRefreshListBinding>() {

    private var page = 0

    private val mAdapter = ArticleAdapter()

    private val showFirstTip by lazy { DataManager.getSquareFirstTip() }

    override fun bindLayoutRes(): Int = R.layout.layout_title_refresh_list

    override fun initData() {}

    override fun initView() {
        mBinding.run {
            mTitleView.setTitle("分享广场")

            mRvList.run {
                layoutManager = LinearLayoutManager(mContext)
                adapter = mAdapter
            }

        }
    }

    override fun initListener() {
        mBinding.run {
            mRefreshLayout.onRefreshAndLoadMore({
                page = 0
                mViewModel.getSquareList(page)
            }, {
                mViewModel.getSquareList(page)
            })
        }

        mAdapter.onItemChildClick { viewId, data ->
            if (viewId == R.id.mItemArticle) WebActivity.start(mContext, data.id, data.title, data.link)
        }
        mAdapter.onItemChildLongClick { viewId, data ->
            if (viewId == R.id.mItemArticle) UserSquareActivity.start(mContext, data.userId)
        }

        observe(mViewModel.articleLiveData, this::onArticle)
    }

    override fun start() {
        mViewModel.getSquareList(page)
    }

    /**
     * 文章列表返回
     * @param data PageList<Article>
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

        // 如果是第一次进入那么显示提示弹窗
        if (!data.isEmpty() && showFirstTip) {
            DialogHelper.showSquareFirstTip(mContext)
        }
    }


}