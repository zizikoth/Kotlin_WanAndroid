package com.module.home.ui.adapter

import android.widget.ImageView
import com.base.base.entity.remote.HomeBanner
import com.frame.core.utils.extra.load
import com.module.home.R
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder

/**
 * title:Banner适配器
 * describe:
 *
 * @author memo
 * @date 2021-02-03 5:17 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class BannerAdapter : BaseBannerAdapter<HomeBanner>() {

    override fun bindData(holder: BaseViewHolder<HomeBanner>, data: HomeBanner, position: Int, pageSize: Int) {
        holder.findViewById<ImageView>(R.id.mIvBanner).load(data.imagePath, R.color.color_BBBBBB)
    }


    override fun getLayoutId(viewType: Int): Int = R.layout.layout_item_banner
}
