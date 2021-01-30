package com.base.base.binding

import android.view.View
import androidx.databinding.BindingAdapter
import com.frame.core.utils.extra.setVisible


@BindingAdapter("visibleOrGone")
fun bindView(view: View, visibleOrGone: Boolean?) {
    view.setVisible(visibleOrGone != false)
}