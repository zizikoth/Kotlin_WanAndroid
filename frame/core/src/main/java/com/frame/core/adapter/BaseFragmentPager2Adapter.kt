package com.frame.core.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-12-18 11:51 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class BaseFragmentPager2Adapter : FragmentStateAdapter {

    constructor(activity: FragmentActivity) : super(activity)

    constructor(fragment: Fragment) : super(fragment)

    private val fragments: ArrayList<Fragment> = arrayListOf()

    fun setData(fragments: ArrayList<Fragment>): com.frame.core.adapter.BaseFragmentPager2Adapter {
        this.fragments.clear()
        this.fragments.addAll(fragments)
        return this
    }

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}