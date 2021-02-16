package com.business.common.ui.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.base.base.entity.remote.*
import com.base.base.utils.IconHelper
import com.base.base.utils.onItemClick
import com.base.base.widget.recyclerview.StartSnapHelper
import com.business.common.R
import com.chad.library.adapter.base.BaseProviderMultiAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.daimajia.swipe.SwipeLayout
import com.frame.core.utils.extra.*

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

class ArticleAdapter : BaseProviderMultiAdapter<Article>() {

    init {
        addItemProvider(GridProvider())
        addItemProvider(TitleProvider())
        addItemProvider(NewArticleProvider())
        addItemProvider(NormalArticleProvider())
        addChildClickViewIds(R.id.mItemDelete, R.id.mItemGrid, R.id.mItemTitle, R.id.mItemArticle)
        addChildLongClickViewIds(R.id.mItemArticle)
    }

    var onNewArticleClick: (data: Article) -> Unit = {}
    var enableSwipe = false

    override fun getItemType(data: List<Article>, position: Int): Int {
        return data[position].itemType
    }

}

private class GridProvider(
    override val itemViewType: Int = HOME_TYPE_SYSTEM_GRID,
    override val layoutId: Int = R.layout.layout_item_system_grid) : BaseItemProvider<Article>() {
    override fun convert(helper: BaseViewHolder, item: Article) {
        helper.setText(R.id.mTvTitle, item.title)
        helper.setImageResource(R.id.mIvLogo, IconHelper.randomIcon(item.id))
    }
}

private class TitleProvider(
    override val itemViewType: Int = HOME_TYPE_TITLE,
    override val layoutId: Int = R.layout.layout_item_title) : BaseItemProvider<Article>() {
    override fun convert(helper: BaseViewHolder, item: Article) {
        helper.run {
            setText(R.id.mTvTitle, item.title)
            setVisible(R.id.mTvMore, item.hasMore)
            getView<TextView>(R.id.mTvMore).round(color(R.color.color_main), 12.5f.dp2px)
        }
    }
}

private class NewArticleProvider(
    override val itemViewType: Int = HOME_TYPE_NEW_ARTICLE,
    override val layoutId: Int = R.layout.layout_item_new_article) : BaseItemProvider<Article>() {

    private val mAdapter = NewArticleAdapter().apply {
        onItemClick { (super.getAdapter() as ArticleAdapter).onNewArticleClick.invoke(it) }
    }

    override fun convert(helper: BaseViewHolder, item: Article) {
        helper.getView<RecyclerView>(R.id.mRvArticle).run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            this.onFlingListener = null
            StartSnapHelper().attachToRecyclerView(this)
            mAdapter.setList(item.newArticles)
            adapter = mAdapter
        }
    }
}

private class NormalArticleProvider(
    override val itemViewType: Int = HOME_TYPE_NORMAL_ARTICLE,
    override val layoutId: Int = R.layout.layout_item_article) : BaseItemProvider<Article>() {

    override fun convert(helper: BaseViewHolder, item: Article) {
        helper.run {

            getView<SwipeLayout>(R.id.mSwipeLayout).isSwipeEnabled = (getAdapter() as ArticleAdapter).enableSwipe

            getView<ImageView>(R.id.mIvIcon).loadCircle(IconHelper.randomAvatar(item.userId))

            setText(R.id.mTvName, item.getCurrentAuthor())

            setText(R.id.mTvTitle, item.title.fromHtml())

            val hasDesc = item.desc.isNotEmpty()
            setGone(R.id.mTvDesc, !hasDesc)
            setText(R.id.mTvDesc, item.desc.fromHtml())

            val hasChapter = item.superChapterName.isNotEmpty() && item.chapterName.isNotEmpty()
            setGone(R.id.mTvChapter, !hasChapter)
            setText(R.id.mTvChapter,
                if (item.superChapterName.isNotEmpty()) "${item.superChapterName} · ${item.chapterName}"
                else item.chapterName)

            val hasPic = item.envelopePic.isNotEmpty()
            setGone(R.id.mIvPic, !hasPic)
            getView<ImageView>(R.id.mIvPic).loadRound(item.envelopePic, 5.dp2px)

            // 有标签 有描述 或者 有标签 无图片
            val showRightTime = (hasDesc || !hasPic) && hasChapter
            setGone(R.id.mTvTime, !showRightTime)
            setGone(R.id.mTvTimeDesc, showRightTime)
            setText(R.id.mTvTime, item.niceDate)
            setText(R.id.mTvTimeDesc, item.niceDate)

        }
    }
}

private class NewArticleAdapter() : BaseQuickAdapter<Article, BaseViewHolder>(R.layout.layout_item_new_article_item_article) {
    override fun convert(holder: BaseViewHolder, item: Article) {
        holder.run {
            setGone(R.id.mNewBg, item.envelopePic.isEmpty())
            setGone(R.id.mNewCover, item.envelopePic.isEmpty())
            if (item.envelopePic.isNotEmpty()) {
                getView<ImageView>(R.id.mNewBg).loadRound(item.envelopePic, 8.dp2px)
                getView<ImageView>(R.id.mNewCover).loadRound(item.envelopePic, 8.dp2px)
            }
            setText(R.id.mNewTitle, item.title)

            setText(R.id.mNewDesc,
                when {
                    item.desc.isNotEmpty() -> item.desc.fromHtml()
                    item.superChapterName.isNotEmpty() -> "${item.superChapterName} · ${item.chapterName}"
                    else -> item.chapterName
                })

        }
    }
}



