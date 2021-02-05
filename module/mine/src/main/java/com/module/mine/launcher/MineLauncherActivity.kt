package com.module.mine.launcher

import androidx.fragment.app.Fragment
import com.base.base.ui.BaseLauncherActivity
import com.module.mine.ui.fragment.MineFragment

/**
 * title:Mine模块启动页面
 * describe:
 *
 * @author memo
 * @date 2021-02-05 14:54
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class MineLauncherActivity : BaseLauncherActivity() {
    override fun bindFragment(): Fragment = MineFragment()
}