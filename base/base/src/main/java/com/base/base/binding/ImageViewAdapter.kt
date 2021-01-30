package com.base.base.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.frame.core.utils.extra.load
import com.frame.core.utils.extra.loadCircle
import com.frame.core.utils.extra.loadRound

@BindingAdapter("url", "circle", "round", requireAll = false)
fun bindImageView(view: ImageView, url: Any?, circle: Boolean?, round: Int?) {
    if (url == null) return
    if (circle != null) {
        view.loadCircle(url)
    } else if (round != null) {
        view.loadRound(url, round)
    } else {
        view.load(url)
    }
}

