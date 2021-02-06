package com.module.mine.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.module.mine.ui.fragment.login.LoginFragment
import com.module.mine.ui.fragment.login.RegisterFragment

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-06 5:56 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class LoginFragmentAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = if (position == 0) LoginFragment() else RegisterFragment()
}