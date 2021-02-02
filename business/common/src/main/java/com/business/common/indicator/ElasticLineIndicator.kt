package com.business.common.indicator

import android.content.Context
import android.util.TypedValue
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import com.business.common.R
import com.frame.core.utils.extra.color
import com.frame.core.utils.extra.dp2px
import com.frame.core.utils.extra.sp2px
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
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
class ElasticLineIndicator(context: Context) : CommonNavigator(context) {

    private val titles = arrayListOf<String>()
    private var onTabClick: (index: Int) -> Unit = {}

    override fun onAttachToMagicIndicator() {
        super.onAttachToMagicIndicator()
        adapter = object : CommonNavigatorAdapter() {
            override fun getCount(): Int = titles.size

            override fun getTitleView(context: Context, index: Int): IPagerTitleView {
                return ColorFlipPagerTitleView(context).apply {
                    text = titles[index]
                    setTextSize(TypedValue.COMPLEX_UNIT_PX, 15.sp2px.toFloat())
                    paint.isFakeBoldText = true
                    normalColor = color(R.color.tab_normal)
                    selectedColor = color(R.color.tab_select)
                    setOnClickListener { onTabClick.invoke(index) }
                }
            }

            override fun getIndicator(context: Context): IPagerIndicator {
                return LinePagerIndicator(context).apply {
                    mode = LinePagerIndicator.MODE_EXACTLY
                    lineHeight = 6.dp2px.toFloat()
                    lineWidth = 10.dp2px.toFloat()
                    roundRadius = 3.dp2px.toFloat()
                    startInterpolator = AccelerateInterpolator()
                    endInterpolator = DecelerateInterpolator(2f)
                    setColors(color(R.color.tab_select))
                }
            }
        }
    }

    fun setOnTabClickListener(onTabClickListener: (index: Int) -> Unit): ElasticLineIndicator {
        this.onTabClick = onTabClickListener
        return this
    }

    fun setTitles(titles: List<String>): ElasticLineIndicator {
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
    }

}