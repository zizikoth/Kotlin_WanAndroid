package com.module.mine.ui.activity.coin

import androidx.recyclerview.widget.LinearLayoutManager
import com.base.base.ui.BaseVmActivity
import com.business.common.databinding.LayoutTitleRefreshListBinding
import com.module.mine.R
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

    override fun bindLayoutRes(): Int = R.layout.layout_title_refresh_list

    override fun initData() {}

    override fun initView() {
        mBinding.run {
            mTitleView.setTitle("积分记录")
            mRvList.run {
                layoutManager = LinearLayoutManager(mContext)

            }
        }
    }

    override fun initListener() {
    }

    override fun start() {
    }


}