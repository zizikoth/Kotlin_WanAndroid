package com.module.home.launcher

import androidx.fragment.app.Fragment
import com.base.base.ui.BaseLauncherActivity
import com.module.home.ui.fragment.home.HomeFragment

/**
 * title:Home模块启动页
 * describe:
 *
 * @author memo
 * @date 2021-01-30 13:49
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class HomeLauncherActivity : BaseLauncherActivity() {

    override fun bindFragment(): Fragment = HomeFragment()
}