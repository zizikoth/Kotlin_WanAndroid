package com.base.base.manager

import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter

/**
 * title:路由管理
 * describe:
 *
 * @author memo
 * @date 2020-12-18 5:34 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
object RouterManager {
    object Home {
        const val HOME_FRAGMENT = "/Home/HomeFragment"
    }

    object Project {
        const val PROJECT_FRAGMENT = "/Project/ProjectFragment"
    }

    object System {
        const val SYSTEM_FRAGMENT = "/System/SystemFragment"
    }

    object Mine {
        const val MINE_FRAGMENT = "/Mine/MineFragment"
        const val LOGIN_ACTIVITY = "/Mine/LoginActivity"
    }

    /**
     * 获取首页页面
     * @return Fragment
     */
    @JvmStatic
    fun getHomeFragment(): Fragment {
        return ARouter.getInstance().build(Home.HOME_FRAGMENT).navigation() as Fragment
    }

    /**
     * 获取项目页面
     * @return Fragment
     */
    @JvmStatic
    fun getProjectFragment(): Fragment {
        return ARouter.getInstance().build(Project.PROJECT_FRAGMENT).navigation() as Fragment
    }

    /**
     * 获取系统页面
     * @return Fragment
     */
    @JvmStatic
    fun getSystemFragment(): Fragment {
        return ARouter.getInstance().build(System.SYSTEM_FRAGMENT).navigation() as Fragment
    }

    /**
     * 获取我的页面
     * @return Fragment
     */
    @JvmStatic
    fun getMineFragment(): Fragment {
        return ARouter.getInstance().build(Mine.MINE_FRAGMENT).navigation() as Fragment
    }

    /**
     * 跳转到登陆界面
     */
    @JvmStatic
    fun startLogin() {
        ARouter.getInstance().build(Mine.LOGIN_ACTIVITY).navigation()
    }


}