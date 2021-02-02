package com.module.blog.launcher

import androidx.fragment.app.Fragment
import com.base.base.ui.BaseLauncherActivity
import com.module.blog.ui.fragment.BlogFragment

/**
 * title:Blog模块启动页
 * describe:
 *
 * @author memo
 * @date 2021-02-01 11:06
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class BlogLauncherActivity : BaseLauncherActivity() {
    override fun bindFragment(): Fragment = BlogFragment()
}