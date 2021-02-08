package com.module.mine.ui.activity.coin

import androidx.recyclerview.widget.LinearLayoutManager
import com.base.base.entity.remote.RankList
import com.base.base.ui.BaseVmActivity
import com.base.base.utils.showEmpty
import com.base.base.widget.recyclerview.RecyclerItemDecoration
import com.business.common.databinding.LayoutTitleRefreshListBinding
import com.frame.core.utils.extra.finish
import com.frame.core.utils.extra.observe
import com.frame.core.utils.extra.onRefreshAndLoadMore
import com.module.mine.R
import com.module.mine.ui.adapter.RankAdapter
import com.module.mine.viewmodel.RankViewModel

/**
 * title:积分排名
 * describe:
 *
 * @author memo
 * @date 2021-02-08 16:13
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class RankActivity : BaseVmActivity<RankViewModel, LayoutTitleRefreshListBinding>() {

    private var page = 1
    private val mAdapter = RankAdapter()

    override fun bindLayoutRes(): Int = R.layout.layout_title_refresh_list

    override fun initData() {}

    override fun initView() {
        mBinding.run {
            mTitleView.setTitle("积分排名")
            mRvList.run {
                setHasFixedSize(true)
                addItemDecoration(RecyclerItemDecoration.Builder(mContext).defaultBuild())
                layoutManager = LinearLayoutManager(mContext)
                adapter = mAdapter
            }
        }
    }

    override fun initListener() {
        mBinding.mRefreshLayout.onRefreshAndLoadMore({
            page = 1
            mViewModel.getRank(page)
        }, {
            mViewModel.getRank(page)
        })

        observe(mViewModel.rankLiveData, this::onRank)
    }

    override fun start() {
        mViewModel.getRank(page)
    }

    private fun onRank(data: RankList) {
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