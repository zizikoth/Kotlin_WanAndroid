package com.module.mine.ui.adapter

import com.base.base.entity.remote.RankInfo
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.frame.core.utils.extra.doTryCatch
import com.module.mine.R

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-08 4:27 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class RankAdapter : BaseQuickAdapter<RankInfo, BaseViewHolder>(R.layout.layout_item_rank) {

    override fun convert(holder: BaseViewHolder, item: RankInfo) {
        holder.run {
            doTryCatch({
                val rank = item.rank.toInt()
                setVisible(R.id.mIvRank, rank <= 3)
                setVisible(R.id.mTvRank, rank > 3)
                when (rank) {
                    1 -> setImageResource(R.id.mIvRank, R.drawable.ic_first)
                    2 -> setImageResource(R.id.mIvRank, R.drawable.ic_second)
                    3 -> setImageResource(R.id.mIvRank, R.drawable.ic_third)
                    else -> setText(R.id.mTvRank, item.rank)
                }
            }, {
                setVisible(R.id.mIvRank, false)
                setVisible(R.id.mTvRank, true)
                setText(R.id.mTvRank, item.rank)
            })
            setText(R.id.mTvName, item.username)
            setText(R.id.mTvCoin, item.coinCount.toString())
        }
    }
}