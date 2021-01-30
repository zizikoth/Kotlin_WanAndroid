package com.module.main.ui.activity

import com.base.base.ui.BaseActivity
import com.frame.core.utils.FragmentHelper
import com.module.main.R
import com.module.main.databinding.ActivityMainBinding

/**
 * title:首页
 * describe:
 *
 * @author memo
 * @date 2021-01-30 13:59
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val fragmentHelper by lazy { FragmentHelper(this, R.id.mContainer, supportFragmentManager) }

    override fun bindLayoutRes(): Int = R.layout.activity_main

    override fun initialize() {
    }
}