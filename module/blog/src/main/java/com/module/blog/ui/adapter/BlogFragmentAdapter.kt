package com.module.blog.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.module.blog.ui.fragment.BlogItemFragment

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-01 11:03 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class BlogFragmentAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val ids = ArrayList<Int>()

    fun setIds(ids: List<Int>) {
        this.ids.clear()
        this.ids.addAll(ids)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = ids.size

    override fun createFragment(position: Int): Fragment = BlogItemFragment.newInstance(ids[position])

}