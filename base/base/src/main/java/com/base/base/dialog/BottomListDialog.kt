package com.base.base.dialog

import android.app.Dialog
import android.view.Gravity
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.base.base.R
import com.base.base.databinding.DialogBottomListBinding
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.frame.core.adapter.BaseRvAdapter
import com.frame.core.dialog.BaseDialog
import com.frame.core.utils.extra.onClick
import com.frame.core.utils.extra.supportsChangeAnimations

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2020-12-18 4:48 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class BottomListDialog(private val data: ArrayList<String> = arrayListOf()) : BaseDialog<DialogBottomListBinding>() {

    private val mAdapter: BaseRvAdapter<String> =
        object : BaseRvAdapter<String>(R.layout.dialog_bottom_list_item) {
            override fun convert(holder: BaseViewHolder, item: String) {
                holder.setText(R.id.mTvContent, item)
                    .setGone(R.id.mLine, holder.adapterPosition != data.size)
            }
        }

    /*** 绑定布局 ***/
    override fun bindLayoutRes(): Int = R.layout.dialog_bottom_list

    /*** 窗体动画 ***/
    override fun bindAnimStyleRes(): Int = R.style.BottomSlideDialogAnim

    /*** 绑定Dialog ***/
    override fun bindDialog(dialog: Dialog) {
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
    }


    /*** 设置窗体配置 ***/
    override fun bindParams(params: WindowManager.LayoutParams) {
        params.gravity = Gravity.BOTTOM
        params.width = WindowManager.LayoutParams.MATCH_PARENT
    }

    /*** 设置子控件 ***/
    override fun bindView(binding: DialogBottomListBinding) {
        binding.apply {
            mTvClose.onClick { dismiss() }
            mRvContent.run {
                supportsChangeAnimations = false
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                mAdapter.setList(data)
                adapter = mAdapter
            }
        }
    }

    fun setData(data: ArrayList<String>): BottomListDialog {
        mAdapter.setList(data)
        return this
    }

    fun setOnItemClickListener(onItemClick: (position: Int, item: String) -> Unit): BottomListDialog {
        mAdapter.setOnItemClickListener { _, _, position ->
            onItemClick.invoke(position, mAdapter.getItem(position))
            dismiss()
        }
        return this
    }
}