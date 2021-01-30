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

    /**
     * 获取首页HomeFragment
     * @return Fragment
     */
    fun getHomeFragment(): Fragment {
        return ARouter.getInstance().build(Home.HOME_FRAGMENT).navigation() as Fragment
    }
}