package com.frame.core.simple

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition

/**
 * title:Glide下载的结果接收
 * describe:
 *
 * @author memo
 * @date 2019-12-02 11:51
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
open class SimpleGlideTarget(
    width: Int = Target.SIZE_ORIGINAL,
    height: Int = Target.SIZE_ORIGINAL
) : CustomTarget<Bitmap>(width, height) {

    override fun onLoadCleared(placeholder: Drawable?) {}

    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {}

}