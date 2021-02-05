package com.module.main.ui.activity

import com.base.base.manager.RouterManager
import com.base.base.ui.BaseActivity
import com.base.base.utils.toast
import com.frame.core.utils.ClickHelper
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
    private val tab1 by lazy { RouterManager.getHomeFragment() }
    private val tab2 by lazy { RouterManager.getProjectFragment() }
    private val tab3 by lazy { RouterManager.getSystemFragment() }
    private val tab4 by lazy { RouterManager.getMineFragment() }

    override fun transparentStatusBar(): Boolean = true

    override fun bindLayoutRes(): Int = R.layout.activity_main

    override fun initialize() {
        fragmentHelper.add(tab1, tab2, tab3, tab4).show()
        mBinding.mBottomBar.setOnItemChangeListener { menu, position ->
            fragmentHelper.show(position)
        }
    }

    override fun onBackPressed() {
        if (ClickHelper.isDoubleClickExit { toast("再次点击退出应用") }) {
            super.onBackPressed()
        }
    }
}