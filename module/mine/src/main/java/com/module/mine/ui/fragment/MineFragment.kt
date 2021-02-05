package com.module.mine.ui.fragment

import com.alibaba.android.arouter.facade.annotation.Route
import com.base.base.manager.RouterManager
import com.base.base.ui.BaseVmFragment
import com.module.mine.R
import com.module.mine.databinding.FragmentMineBinding
import com.module.mine.viewmodel.MineViewModel

/**
 * title:我的
 * describe:
 *
 * @author memo
 * @date 2021-02-05 15:14
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
@Route(path = RouterManager.Mine.MINE_FRAGMENT)
class MineFragment : BaseVmFragment<MineViewModel, FragmentMineBinding>() {
    /*** 绑定界面 ***/
    override fun bindLayoutRes(): Int = R.layout.fragment_mine

    override fun initData() {
    }

    override fun initView() {
    }

    override fun initListener() {
    }

    override fun start() {
    }
}