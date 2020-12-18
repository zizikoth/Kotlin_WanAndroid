package com.frame.core.adapter

import android.view.View
import androidx.annotation.LayoutRes
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.frame.core.utils.ClickHelper

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-12-18 4:53 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
abstract class BaseRvAdapter<T>(@LayoutRes layoutResId: Int) : BaseQuickAdapter<T, BaseViewHolder>(layoutResId) {

    override fun setOnItemClick(v: View, position: Int) {
        if (ClickHelper.isNotFastClick)
            super.setOnItemClick(v, position)
    }

    override fun setOnItemLongClick(v: View, position: Int): Boolean {
        return if (ClickHelper.isNotFastLongClick)
            super.setOnItemLongClick(v, position)
        else
            false
    }

    override fun setOnItemChildClick(v: View, position: Int) {
        if (ClickHelper.isNotFastClick)
            super.setOnItemChildClick(v, position)
    }

    override fun setOnItemChildLongClick(v: View, position: Int): Boolean {
        return if (ClickHelper.isNotFastLongClick)
            super.setOnItemChildLongClick(v, position)
        else
            false
    }

}