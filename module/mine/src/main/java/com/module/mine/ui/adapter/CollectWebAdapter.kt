package com.module.mine.ui.adapter

import com.base.base.entity.remote.WebInfo
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.daimajia.swipe.SwipeLayout
import com.module.mine.R

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-08 6:55 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class CollectWebAdapter : BaseQuickAdapter<WebInfo, BaseViewHolder>(R.layout.layout_item_collect_web) {

    init {
        addChildClickViewIds(R.id.mIvEdit, R.id.mIvDelete, R.id.mFlContent)
        addChildLongClickViewIds(R.id.mFlContent)
    }

    override fun convert(holder: BaseViewHolder, item: WebInfo) {
        holder.run {
            getView<SwipeLayout>(R.id.mSwipeLayout).close()
            setText(R.id.mTvName, item.name)
            setText(R.id.mTvLink, item.link)
        }
    }
}