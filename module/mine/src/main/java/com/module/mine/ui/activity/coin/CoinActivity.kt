package com.module.mine.ui.activity.coin

import androidx.recyclerview.widget.LinearLayoutManager
import com.base.base.entity.remote.CoinInfo
import com.base.base.entity.remote.PageList
import com.base.base.ui.BaseVmActivity
import com.base.base.utils.showEmpty
import com.base.base.widget.recyclerview.RecyclerItemDecoration
import com.business.common.databinding.LayoutTitleRefreshListBinding
import com.frame.core.utils.extra.finish
import com.frame.core.utils.extra.observe
import com.frame.core.utils.extra.onRefreshAndLoadMore
import com.module.mine.R
import com.module.mine.ui.adapter.CoinAdapter
import com.module.mine.viewmodel.CoinViewModel

/**
 * title:积分记录列表
 * describe:
 *
 * @author memo
 * @date 2021-02-07 14:02
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class CoinActivity : BaseVmActivity<CoinViewModel, LayoutTitleRefreshListBinding>() {

    private var page = 1

    private val mAdapter = CoinAdapter()

    override fun bindLayoutRes(): Int = R.layout.layout_title_refresh_list

    override fun initData() {}

    override fun initView() {
        mBinding.run {
            mTitleView.setTitle("积分记录")
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
            mViewModel.getCoinList(page)
        }, {
            mViewModel.getCoinList(page)
        })
        observe(mViewModel.coinLiveData, this::onCoin)
    }

    override fun start() {
        mViewModel.getCoinList(page)
    }

    /**
     * 获取积分历史记录
     * @param data PageList<CoinInfo>
     */
    private fun onCoin(data: PageList<CoinInfo>) {
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