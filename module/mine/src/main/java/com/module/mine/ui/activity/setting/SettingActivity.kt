package com.module.mine.ui.activity.setting

import com.base.base.ui.BaseVmActivity
import com.base.base.utils.toast
import com.blankj.utilcode.util.CleanUtils
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.FileUtils
import com.frame.core.utils.extra.onClick
import com.frame.core.utils.extra.startActivity
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
        // 缓存文件
        (FileUtils.getLength(mContext.externalCacheDir) + FileUtils.getLength(mContext.cacheDir)).run {
            if (this > 0) mBinding.mItemCache.setItemExtraText(ConvertUtils.byte2FitMemorySize(this))
        }
    }

    override fun initListener() {
        mBinding.run {
            // 退出登录
            mBtnLoginOut.onClick {
                mViewModel.loginOut {
                    startActivity<LoginActivity>()
                    finish()
                }
            }
            // 清理缓存
            mItemCache.onClick {
                CleanUtils.cleanInternalCache()
                CleanUtils.cleanExternalCache()
                mItemCache.setItemExtraText("")
                toast("缓存清理完毕")
            }
        }

    }

    override fun start() {
    }

}