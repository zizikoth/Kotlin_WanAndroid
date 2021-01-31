package com.module.home.ui.adapter

import android.graphics.Color
import android.widget.ImageView
import com.base.base.entity.remote.Article
import com.base.base.utils.IconHelper
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.frame.core.adapter.BaseRvAdapter
import com.frame.core.utils.extra.color
import com.frame.core.utils.extra.dp2px
import com.frame.core.utils.extra.fromHtml
import com.frame.core.utils.extra.loadRound
import com.module.home.R

/**
 * title:文章列表适配器
 * describe:
 *
 * @author memo
 * @date 2021-01-30 4:10 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class ArticleAdapter : BaseRvAdapter<Article>(R.layout.layout_item_article) {

    override fun convert(holder: BaseViewHolder, item: Article) {
        holder.run {
            setImageResource(R.id.mIvIcon, IconHelper.randomIcon(item.chapterId))

            setText(R.id.mTvName, item.getCurrentAuthor())

            setText(R.id.mTvTitle, item.title.fromHtml())

            setText(R.id.mTvDesc, item.desc.fromHtml())

            setText(R.id.mTvChapter, if (item.superChapterName.isNotEmpty()) "${item.superChapterName} · ${item.chapterName}" else item.chapterName)

            setGone(R.id.mTvTime, item.top)
            setText(R.id.mTvTime, item.niceShareDate)

            setBackgroundColor(R.id.mClContent, if (item.top) color(R.color.background_article_top) else Color.WHITE)

            setGone(R.id.mIvPic, item.envelopePic.isEmpty())
            getView<ImageView>(R.id.mIvPic).loadRound(item.envelopePic, 5.dp2px)
        }
    }
}