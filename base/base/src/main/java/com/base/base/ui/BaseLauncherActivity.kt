package com.base.base.ui

import androidx.fragment.app.Fragment
import com.base.base.R
import com.base.base.databinding.ActivityBaseLauncherBinding
import com.frame.core.utils.extra.showFragment

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-01-30 6:00 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
abstract class BaseLauncherActivity : BaseActivity<ActivityBaseLauncherBinding>() {

    abstract fun bindFragment(): Fragment
    override fun bindLayoutRes(): Int = R.layout.activity_base_launcher
    override fun transparentStatusBar(): Boolean = true
    override fun initialize() {
        showFragment(R.id.mContainer, bindFragment())
    }
}