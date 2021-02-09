package com.frame.core.utils.dbadapter

import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.databinding.BindingAdapter
import com.frame.core.utils.extra.*

/**
 * title:自定义DataBindingAdapter
 * describe:
 *
 * @author memo
 * @date 2021-02-10 1:26 AM
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
object DataBindingAdapter {

    @JvmStatic
    @BindingAdapter(value = ["app:round_radius", "app:round_color"], requireAll = true)
    fun bindView(view: View, round_radius: Int, @ColorRes round_color: Int) {
        view.round(round_color, round_radius.dp2px)
    }

    @JvmStatic
    @BindingAdapter(value = ["app:url", "app:circle", "app:round"], requireAll = false)
    fun bindImageView(view: ImageView, url: String, circle: Boolean?, round: Int?) {
        if (circle == true) {
            view.loadCircle(url)
        } else if (round != null) {
            view.loadRound(url, round.dp2px)
        } else {
            view.load(url)
        }
    }
}