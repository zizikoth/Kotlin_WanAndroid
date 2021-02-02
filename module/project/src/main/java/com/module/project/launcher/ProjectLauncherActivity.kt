package com.module.project.launcher

import androidx.fragment.app.Fragment
import com.base.base.ui.BaseLauncherActivity
import com.module.project.ui.fragment.project.ProjectFragment

/**
 * title:Project模块启动页
 * describe:
 *
 * @author memo
 * @date 2021-01-31 23:21
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class ProjectLauncherActivity : BaseLauncherActivity() {
    override fun bindFragment(): Fragment = ProjectFragment()
}