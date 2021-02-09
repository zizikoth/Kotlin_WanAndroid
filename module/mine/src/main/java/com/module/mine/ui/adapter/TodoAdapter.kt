package com.module.mine.ui.adapter

import android.widget.ImageView
import android.widget.LinearLayout
import com.base.base.entity.local.TodoStatus
import com.base.base.entity.local.TodoType
import com.base.base.entity.remote.TodoInfo
import com.base.base.widget.label.LabelView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.daimajia.swipe.SwipeLayout
import com.frame.core.utils.extra.circle
import com.frame.core.utils.extra.color
import com.frame.core.utils.extra.dp2px
import com.frame.core.utils.extra.round
import com.module.mine.R

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-09 3:53 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class TodoAdapter : BaseQuickAdapter<TodoInfo, BaseViewHolder>(R.layout.layout_item_todo) {

    private val radius = 8.dp2px

    init {
        addChildClickViewIds(R.id.mLlContent, R.id.mIvStatus, R.id.mIvEdit, R.id.mIvDelete)
    }

    override fun convert(holder: BaseViewHolder, item: TodoInfo) {
        holder.run {
            getView<SwipeLayout>(R.id.mSwipeLayout).close()
            val color = when (item.type) {
                TodoType.WORK.value -> color(R.color.color_todo_work)
                TodoType.LIFE.value -> color(R.color.color_todo_life)
                TodoType.MINE.value -> color(R.color.color_todo_mine)
                TodoType.OTHER.value -> color(R.color.color_todo_other)
                else -> color(R.color.color_todo_other)
            }
            getView<LinearLayout>(R.id.mLlContent).round(color, radius)
            getView<LabelView>(R.id.mLabelView).text = item.getTypeContent()
            setImageResource(R.id.mIvStatus, if (item.status == TodoStatus.COMPLETE.value) R.drawable.ic_uncomplete else R.drawable.ic_complete)

            setText(R.id.mTvTitle, item.title)
            setText(R.id.mTvContent, item.content)
            setText(R.id.mTvTimePriority, item.dateStr + "        [ " + item.getPriorityContent() + " ]")
            setVisible(R.id.mIvComplete, item.status == TodoStatus.COMPLETE.value)

            getView<ImageView>(R.id.mIvStatus).circle(color(R.color.color_status_background))
            getView<ImageView>(R.id.mIvEdit).circle(color(R.color.color_edit_background))
            getView<ImageView>(R.id.mIvDelete).circle(color(R.color.color_delete_background))
        }
    }

    fun close(position: Int) {
        (getViewByPosition(position, R.id.mSwipeLayout) as SwipeLayout?)?.close()
    }
}