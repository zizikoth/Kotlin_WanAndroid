package com.frame.core.adapter

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder

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

}