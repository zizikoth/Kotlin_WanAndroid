package com.module.project.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.module.project.ui.fragment.project.ProjectItemFragment

/**
 * title:项目文章列表适配器
 * describe:
 *
 * @author memo
 * @date 2021-02-01 10:28 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class ProjectFragmentAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val ids = ArrayList<Int>()

    fun setIds(ids: List<Int>) {
        this.ids.clear()
        this.ids.addAll(ids)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = ids.size

    override fun createFragment(position: Int): Fragment = ProjectItemFragment.newInstance(ids[position])
}