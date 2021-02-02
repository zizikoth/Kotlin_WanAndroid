package com.module.system.ui.adapter

import com.base.base.entity.remote.SystemTreeItem
import com.base.base.entity.remote.TYPE_SYSTEM_ITEM
import com.base.base.entity.remote.TYPE_SYSTEM_TITLE
import com.chad.library.adapter.base.BaseProviderMultiAdapter
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.frame.core.utils.extra.color
import com.frame.core.utils.extra.dp2px
import com.frame.core.utils.extra.round
import com.module.system.R

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-02 2:26 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class SystemAdapter : BaseProviderMultiAdapter<SystemTreeItem>() {

    init {
        addItemProvider(SystemTitleProvider())
        addItemProvider(SystemItemProvider())
    }

    override fun getItemType(data: List<SystemTreeItem>, position: Int): Int {
        return data[position].multiType
    }
}

/**
 * 标题
 */
class SystemTitleProvider() : BaseItemProvider<SystemTreeItem>() {
    override val itemViewType: Int = TYPE_SYSTEM_TITLE
    override val layoutId: Int = R.layout.layout_item_system_title
    override fun convert(helper: BaseViewHolder, item: SystemTreeItem) {
        helper.setText(R.id.mTvTitle, item.getContent())
    }
}

/**
 * 条目
 */
class SystemItemProvider : BaseItemProvider<SystemTreeItem>() {
    override val itemViewType: Int = TYPE_SYSTEM_ITEM
    override val layoutId: Int = R.layout.layout_item_system_item

    override fun convert(helper: BaseViewHolder, item: SystemTreeItem) {
        helper.setText(R.id.mTvItem, item.getContent())
            .itemView.round(color(R.color.color_BBBBBB), 8.dp2px)
    }

}