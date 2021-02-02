package com.module.system.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.module.system.ui.fragment.system.NavigationItemFragment
import com.module.system.ui.fragment.system.SystemItemFragment

/**
 * title:体系页面适配器
 * describe:
 *
 * @author memo
 * @date 2021-02-01 4:57 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class SystemFragmentAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = if (position == 0) SystemItemFragment() else NavigationItemFragment()
}