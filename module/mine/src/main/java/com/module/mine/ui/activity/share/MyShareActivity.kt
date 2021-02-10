package com.module.mine.ui.activity.share

import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.base.base.entity.remote.UserSquareShare
import com.base.base.ui.BaseVmActivity
import com.base.base.utils.onItemChildClick
import com.base.base.utils.showEmpty
import com.business.common.databinding.LayoutTitleRefreshListBinding
import com.business.common.ui.activity.web.WebActivity
import com.business.common.ui.adapter.ArticleAdapter
import com.business.common.ui.dialog.DialogHelper
import com.frame.core.utils.extra.finish
import com.frame.core.utils.extra.observe
import com.frame.core.utils.extra.onRefreshAndLoadMore
import com.module.mine.R
import com.module.mine.databinding.HeaderUserSquareBinding
import com.module.mine.viewmodel.ShareViewModel

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-10 9:58 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class MyShareActivity : BaseVmActivity<ShareViewModel, LayoutTitleRefreshListBinding>() {

    private val mHeaderBinding: HeaderUserSquareBinding by lazy {
        DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.header_user_square, null, false)
    }

    private var page = 1

    private val mAdapter = ArticleAdapter()

    override fun bindLayoutRes(): Int = R.layout.layout_title_refresh_list

    override fun initData() {
    }

    override fun initView() {
        mBinding.run {
            mTitleView.setTitle("我的分享")
            mTitleView.setRightDrawable(R.drawable.ic_collect_add)

            mRvList.run {
                layoutManager = LinearLayoutManager(mContext)
                mAdapter.addHeaderView(mHeaderBinding.root)
                mAdapter.enableSwipe = true
                mAdapter.headerWithEmptyEnable = true
                adapter = mAdapter
            }
        }
    }

    override fun initListener() {

        mBinding.mTitleView.setOnRightClickListener {
            DialogHelper.showShareArticle(mContext) { dialog, title, link ->
                mViewModel.addShare(dialog, title, link)
            }
        }

        mAdapter.onItemChildClick { viewId, data ->
            when (viewId) {
                R.id.mItemArticle -> WebActivity.start(mContext, data.id, data.title, data.link)
                R.id.mItemDelete -> mViewModel.deleteShare(data.id)
            }
        }

        mBinding.mRefreshLayout.onRefreshAndLoadMore({
            page = 1
            mViewModel.getShareList(page)
        }, {
            mViewModel.getShareList(page)
        })

        observe(mViewModel.listLiveData, this::onShareList)
        observe(mViewModel.resultLiveData, this::onShareResult)
    }

    override fun start() {
        mViewModel.getShareList(page)
    }

    /**
     * 用户分享列表
     * @param detail UserSquareShare
     */
    private fun onShareList(detail: UserSquareShare) {
        mHeaderBinding.info = detail.coinInfo
        val data = detail.shareArticles
        mBinding.mRefreshLayout.finish(data.hasMore())
        if (page == 1) {
            mAdapter.setList(data.datas)
            mAdapter.showEmpty(mContext, data.isEmpty())
        } else {
            mAdapter.addData(data.datas)
        }
        if (data.hasMore()) page++
    }

    /**
     * 新增删除操作
     * @param id Int
     */
    private fun onShareResult(id: Int) {
        // 新增
        if (id == -1) mBinding.mRefreshLayout.autoRefresh()
        // 删除
        else {
            mAdapter.data.findLast { id == it.id }?.let { mAdapter.remove(it) }
            if (mAdapter.data.isEmpty()) mBinding.mRefreshLayout.autoRefresh()
        }
    }

}