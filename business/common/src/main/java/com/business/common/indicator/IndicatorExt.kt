package com.business.common.indicator

import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import net.lucode.hackware.magicindicator.MagicIndicator

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-01 12:13 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
enum class IndicatorType {
    ElasticLine,
    RoundCover,
}

fun MagicIndicator.init(vp2: ViewPager2, type: IndicatorType) {
    val indicator = if (type == IndicatorType.ElasticLine) {
        ElasticLineIndicator(context).setOnTabClickListener { vp2.currentItem = it }
    } else {
        RoundCoverIndicator(context).setOnTabClickListener { vp2.currentItem = it }
    }
    vp2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrollStateChanged(state: Int) {
            indicator.onPageScrollStateChanged(state)
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            indicator.onPageScrolled(position, positionOffset, positionOffsetPixels)
        }

        override fun onPageSelected(position: Int) {
            indicator.onPageSelected(position)
        }

    })
    this.navigator = indicator
}

fun MagicIndicator.setTitles(titles: List<String>, type: IndicatorType) {
    if (type == IndicatorType.ElasticLine) {
        (this.navigator as ElasticLineIndicator).setTitles(titles)
    } else if (type == IndicatorType.RoundCover) {
        (this.navigator as RoundCoverIndicator).setTitles(titles)
    }
}