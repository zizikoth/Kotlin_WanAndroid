package com.business.common.indicator

import android.content.Context
import com.business.common.R
import com.frame.core.utils.extra.color
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.WrapPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView

/**
 * title:项目通用Indicator
 * describe:
 *
 * @author memo
 * @date 2021-01-31 11:50 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class CommonIndicator(context: Context) : CommonNavigator(context) {

    private val titles = arrayListOf<String>()
    private var onTabClick: (index: Int) -> Unit = {}

    override fun onAttachToMagicIndicator() {
        super.onAttachToMagicIndicator()
        adapter = object : CommonNavigatorAdapter() {
            override fun getCount(): Int = titles.size

            override fun getTitleView(context: Context, index: Int): IPagerTitleView {
                val tab = ColorFlipPagerTitleView(context)
                tab.text = titles[index]
                tab.normalColor = color(R.color.tab_normal)
                tab.selectedColor = color(R.color.tab_select)
                tab.setOnClickListener { onTabClick.invoke(index) }
                return tab
            }

            override fun getIndicator(context: Context): IPagerIndicator {
                val indicator = WrapPagerIndicator(context)
                indicator.fillColor = color(R.color.tab_select)
                return indicator
            }
        }
    }

    fun setOnTabClickListener(onTabClickListener: (index: Int) -> Unit): CommonIndicator {
        this.onTabClick = onTabClickListener
        return this
    }

    fun setTitles(titles: ArrayList<String>): CommonIndicator {
        this.titles.clear()
        this.titles.addAll(titles)
        notifyDataSetChanged()
        return this
    }


    inner class ColorFlipPagerTitleView(context: Context?) : SimplePagerTitleView(context) {
        private var mChangePercent = 0.5f
        override fun onLeave(index: Int, totalCount: Int, leavePercent: Float, leftToRight: Boolean) {
            if (leavePercent >= mChangePercent) {
                setTextColor(mNormalColor)
            } else {
                setTextColor(mSelectedColor)
            }
        }

        override fun onEnter(index: Int, totalCount: Int, enterPercent: Float, leftToRight: Boolean) {
            if (enterPercent >= mChangePercent) {
                setTextColor(mSelectedColor)
            } else {
                setTextColor(mNormalColor)
            }
        }

        override fun onSelected(index: Int, totalCount: Int) {}
        override fun onDeselected(index: Int, totalCount: Int) {}
        fun getChangePercent(): Float = mChangePercent
        fun setChangePercent(changePercent: Float) {
            mChangePercent = changePercent
        }
    }

}