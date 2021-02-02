package com.business.common.indicator

import android.content.Context
import android.graphics.Color
import com.business.common.R
import com.frame.core.utils.extra.color
import com.frame.core.utils.extra.dp2px
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-01 4:37 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class RoundCoverIndicator(context: Context) : CommonNavigator(context) {
    private val titles = arrayListOf<String>()
    private var onTabClick: (index: Int) -> Unit = {}

    override fun onAttachToMagicIndicator() {
        super.onAttachToMagicIndicator()
        adapter = object : CommonNavigatorAdapter() {
            override fun getCount(): Int = titles.size

            override fun getTitleView(context: Context, index: Int): IPagerTitleView {
                return ClipPagerTitleView(context).apply {
                    text = titles[index]
                    textColor = color(R.color.tab_normal)
                    clipColor = Color.WHITE
                    setOnClickListener { onTabClick.invoke(index) }
                }
            }

            override fun getIndicator(context: Context): IPagerIndicator {
                return LinePagerIndicator(context).apply {
                    val navigationHeight = 30.dp2px.toFloat()
                    val borderWidth = 1.dp2px.toFloat()
                    lineHeight = navigationHeight - 2 * borderWidth
                    roundRadius = lineHeight / 2f
                    yOffset = borderWidth
                    setColors(color(R.color.tab_select))
                }
            }

        }
    }

    fun setOnTabClickListener(onTabClickListener: (index: Int) -> Unit): RoundCoverIndicator {
        this.onTabClick = onTabClickListener
        return this
    }

    fun setTitles(titles: List<String>): RoundCoverIndicator {
        this.titles.clear()
        this.titles.addAll(titles)
        notifyDataSetChanged()
        return this
    }

}