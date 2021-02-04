package com.base.base.widget.empty

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import com.base.base.R
import com.base.base.databinding.LayoutStatusDataEmptyBinding

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2021-02-01 9:14 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class EmptyView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var binding: LayoutStatusDataEmptyBinding

    init {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.layout_status_data_empty,this,true)
    }

    fun setTip(tip: String): EmptyView {
        binding.mTvEmpty.text = tip
        return this
    }
}