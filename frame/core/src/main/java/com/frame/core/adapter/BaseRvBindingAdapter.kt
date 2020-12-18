package com.frame.core.adapter

import android.view.View
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.frame.core.utils.ClickHelper

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-12-18 11:47 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
abstract class BaseRvBindingAdapter<T, BD : ViewDataBinding>(@LayoutRes layoutResId: Int) :
    BaseQuickAdapter<T, BaseDataBindingHolder<BD>>(layoutResId) {

    override fun convert(holder: BaseDataBindingHolder<BD>, item: T) {
        holder.dataBinding?.apply {
            converts(this, item)
            executePendingBindings()
        }
    }

    protected abstract fun converts(binding: BD, item: T)

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