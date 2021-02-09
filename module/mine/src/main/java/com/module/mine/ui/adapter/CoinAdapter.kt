package com.module.mine.ui.adapter

import com.base.base.entity.remote.CoinInfo
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.module.mine.R


class CoinAdapter : BaseQuickAdapter<CoinInfo, BaseViewHolder>(R.layout.layout_item_coin) {

    override fun convert(holder: BaseViewHolder, item: CoinInfo) {
        holder.run {
            setText(R.id.mTvName, item.getName())
            setText(R.id.mTvCoin, if (item.coinCount > 0) "+${item.coinCount}" else "-${item.coinCount}")
            setText(R.id.mTvReason, item.desc)
        }
    }
}