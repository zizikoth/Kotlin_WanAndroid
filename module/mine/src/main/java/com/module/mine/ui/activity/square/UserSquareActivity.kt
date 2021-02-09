package com.module.mine.ui.activity.square

import android.content.Context
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
import com.frame.core.utils.extra.finish
import com.frame.core.utils.extra.observe
import com.frame.core.utils.extra.onRefreshAndLoadMore
import com.frame.core.utils.extra.startActivity
import com.module.mine.R
import com.module.mine.databinding.HeaderUserSquareBinding
import com.module.mine.viewmodel.SquareViewModel

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-10 2:41 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class UserSquareActivity : BaseVmActivity<SquareViewModel, LayoutTitleRefreshListBinding>() {

    companion object {
        fun start(context: Context, userId: Int) {
            context.startActivity<UserSquareActivity>("userId" to userId)
        }
    }

    private val mHeaderBinding: HeaderUserSquareBinding by lazy {
        DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.header_user_square, null, false)
    }

    private var page = 0
    private var userId = 0

    private val mAdapter = ArticleAdapter()

    override fun bindLayoutRes(): Int = R.layout.layout_title_refresh_list

    override fun initData() {
        userId = intent.getIntExtra("userId", userId)
    }

    override fun initView() {
        mBinding.run {
            mTitleView.setTitle("用户分享")

            mRvList.run {
                layoutManager = LinearLayoutManager(mContext)
                mAdapter.addHeaderView(mHeaderBinding.root)
                adapter = mAdapter
            }
        }
    }

    override fun initListener() {
        mAdapter.onItemChildClick { viewId, data ->
            if (viewId == R.id.mItemArticle) WebActivity.start(mContext, data.id, data.title, data.link)
        }

        mBinding.mRefreshLayout.onRefreshAndLoadMore({
            page = 0
            mViewModel.getSquareUserShareList(userId, page)
        }, {
            mViewModel.getSquareUserShareList(userId, page)
        })

        observe(mViewModel.shareLiveData, this::onShareSquare)
    }

    override fun start() {
        mViewModel.getSquareUserShareList(userId, page)
    }

    /**
     * 用户分享列表
     * @param detail UserSquareShare
     */
    private fun onShareSquare(detail: UserSquareShare) {
        mHeaderBinding.info = detail.coinInfo
        val data = detail.shareArticles
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