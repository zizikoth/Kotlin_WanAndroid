package com.frame.core.utils.extra

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.module.AppGlideModule
import com.frame.core.core.CoreApp

@GlideModule
class GlideAppModule : AppGlideModule()

fun ImageView.load(url: Any) {
    GlideApp.with(this).load(url).into(this)
}

fun ImageView.load(url: Any, holderRes: Int) {
    GlideApp.with(this).load(url)
        .placeholder(holderRes).error(holderRes)
        .centerCrop()
        .into(this)
}

fun ImageView.loadRound(url: Any, radius: Int) {
    GlideApp.with(this).load(url)
        .placeholder(ColorDrawable(Color.LTGRAY)).error(ColorDrawable(Color.LTGRAY))
        .transform(CenterCrop(), RoundedCorners(radius))
        .into(this)
}

fun ImageView.loadCircle(url: Any) {
    GlideApp.with(this).load(url)
        .placeholder(ColorDrawable(Color.LTGRAY)).error(ColorDrawable(Color.LTGRAY))
        .transform(CenterCrop(), CircleCrop())
        .into(this)
}

fun clearGlideDiskCache(owner: LifecycleOwner) {
    owner.doInBackground {
        GlideApp.get(CoreApp.app.applicationContext).clearDiskCache()
    }
}

fun clearGlideMemoryCache() {
    GlideApp.get(CoreApp.app.applicationContext).clearMemory()
}
