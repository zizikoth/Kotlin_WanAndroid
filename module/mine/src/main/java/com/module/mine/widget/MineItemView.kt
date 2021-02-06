package com.module.mine.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import com.module.mine.R
import com.module.mine.databinding.MineItemViewBinding

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-06 2:20 PM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class MineItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private val mBinding = DataBindingUtil.inflate<MineItemViewBinding>(LayoutInflater.from(context), R.layout.mine_item_view, this, true)

    var title: String = ""
        set(value) {
            field = value
            mBinding.mTvTitle.text = field
        }

    var content: String = ""
        set(value) {
            field = value
            mBinding.mTvContent.text = field
        }

    init {
        context.obtainStyledAttributes(attrs, R.styleable.MineItemView).apply {
            title = this.getString(R.styleable.MineItemView_miv_title) ?: title
            content = this.getString(R.styleable.MineItemView_miv_content) ?: content
            recycle()
        }
    }
}