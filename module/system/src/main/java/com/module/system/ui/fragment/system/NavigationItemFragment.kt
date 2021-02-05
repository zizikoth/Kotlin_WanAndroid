package com.module.system.ui.fragment.system

import com.base.base.entity.remote.SystemTreeItem
import com.base.base.entity.remote.TYPE_SYSTEM_ITEM
import com.base.base.ui.BaseVmFragment
import com.base.base.utils.onMultiItemClickListener
import com.business.common.ui.activity.web.WebActivity
import com.frame.core.utils.extra.observe
import com.google.android.flexbox.FlexboxLayoutManager
import com.module.system.R
import com.module.system.databinding.FragmentSystemItemBinding
import com.module.system.ui.adapter.SystemAdapter
import com.module.system.viewmodel.SystemViewModel

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-01 4:57 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class NavigationItemFragment : BaseVmFragment<SystemViewModel, FragmentSystemItemBinding>() {

    private val mAdapter = SystemAdapter()

    /*** 绑定界面 ***/
    override fun bindLayoutRes(): Int = R.layout.fragment_system_item

    override fun initData() {}

    override fun initView() {
        mBinding.mRvList.run {
            layoutManager = FlexboxLayoutManager(mContext)
            adapter = mAdapter
        }
    }

    override fun initListener() {
        mAdapter.onMultiItemClickListener { multiType, data ->
            if (multiType == TYPE_SYSTEM_ITEM) WebActivity.start(mContext, data.title, data.link)
        }
        observe(mViewModel.systemLiveData, this::onNaviTree)
    }

    override fun start() {
        mViewModel.getNaviTree()
    }

    private fun onNaviTree(data: ArrayList<SystemTreeItem>) {
        mAdapter.setList(data)
    }
}