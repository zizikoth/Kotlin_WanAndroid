package com.module.system.launcher

import androidx.fragment.app.Fragment
import com.base.base.ui.BaseLauncherActivity
import com.module.system.ui.fragment.SystemFragment

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-01 3:39 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class SystemLauncherActivity : BaseLauncherActivity() {
    override fun bindFragment(): Fragment = SystemFragment()
}