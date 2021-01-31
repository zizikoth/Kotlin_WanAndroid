package com.business.common.indicator

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
fun MagicIndicator.init(vp2: ViewPager2) {
    val indicator = CommonIndicator(context)
    indicator.setOnTabClickListener { vp2.currentItem = it }
    vp2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrollStateChanged(state: Int) {
            this.onPageScrollStateChanged(state)
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            this.onPageScrolled(position, positionOffset, positionOffsetPixels)
        }

        override fun onPageSelected(position: Int) {
            this.onPageSelected(position)
        }

    })
    this.navigator = indicator
}