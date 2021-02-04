package com.frame.core.adapter

import android.os.Parcelable
import android.util.SparseArray
import android.view.View
import androidx.annotation.LayoutRes
import androidx.core.util.set
import androidx.recyclerview.widget.RecyclerView
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

    /*** 用户存储子RecyclerView的状态 ***/
    private val mStateCache by lazy { SparseArray<Parcelable?>() }

    /**
     * 界面回收的时候进行保存子RecyclerView的状态
     * 对于其他自定义控件 建议进行自定义的保存状态
     * @param holder ViewHolder
     */
    override fun onViewRecycled(holder: BaseViewHolder) {
        bindSaveStateChildRecyclerView(holder)?.let {
            val position = holder.adapterPosition
            val layoutManager: RecyclerView.LayoutManager? = it.layoutManager
            mStateCache[position] = layoutManager?.onSaveInstanceState()
        }
        super.onViewRecycled(holder)
    }

    /**
     * ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
     * 绑定子RecyclerView 保存子RecyclerView状态只有这个需要调用！！！！！！
     * ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
     * @param holder ViewHolder
     * @return RecyclerView?
     */
    protected open fun bindSaveStateChildRecyclerView(holder: BaseViewHolder): RecyclerView? = null

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