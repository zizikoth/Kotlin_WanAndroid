package com.module.mine.ui.activity.setting

import com.base.base.ui.BaseVmActivity
import com.frame.core.utils.extra.*
import com.module.mine.R
import com.module.mine.databinding.ActivitySettingBinding
import com.module.mine.ui.activity.login.LoginActivity
import com.module.mine.viewmodel.SettingViewModel

/**
 * title:设置界面
 * describe:
 *
 * @author memo
 * @date 2021-02-07 16:43
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class SettingActivity : BaseVmActivity<SettingViewModel, ActivitySettingBinding>() {

    override fun bindLayoutRes(): Int = R.layout.activity_setting

    override fun initData() {}

    override fun initView() {
        showContent()
        mBinding.mBtnLoginOut.round(color(R.color.color_shape), 22.5f.dp2px)
    }

    override fun initListener() {
        mBinding.mBtnLoginOut.onClick {
            mViewModel.loginOut {
                startActivity<LoginActivity>()
                finish()
            }
        }
    }

    override fun start() {
    }

}