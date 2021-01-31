package com.module.home.ui.adapter

import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.frame.core.adapter.BaseRvAdapter
import com.frame.core.utils.extra.color
import com.frame.core.utils.extra.dp2px
import com.frame.core.utils.extra.round
import com.module.home.R

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-01-31 11:05 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class SearchAdapter : BaseRvAdapter<String>(R.layout.layout_item_search) {

    override fun convert(holder: BaseViewHolder, item: String) {
        holder.run {
            setText(R.id.mTvText, item)
            itemView.round(color(R.color.color_BBBBBB), 8.dp2px)
        }
    }
}