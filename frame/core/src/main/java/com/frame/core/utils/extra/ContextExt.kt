package com.frame.core.utils.extra

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.blankj.utilcode.util.ConvertUtils
import com.frame.core.core.CoreApp

/**
 * title: 获取资源文件
 * describe:
 *
 * @author zhou
 * @date 2019-01-29 14:43
 */

fun color(id: Int): Int =
    ContextCompat.getColor(CoreApp.app.applicationContext, id)

fun string(id: Int): String =
    CoreApp.app.applicationContext.resources.getString(id)

fun stringArray(id: Int): Array<String> =
    CoreApp.app.applicationContext.resources.getStringArray(id)

fun drawable(id: Int) =
    ContextCompat.getDrawable(CoreApp.app.applicationContext, id)

fun dimen(id: Int) =
    CoreApp.app.applicationContext.resources.getDimension(id)

val Int.dp2px get() = ConvertUtils.dp2px(this.toFloat())
val Int.dp2pxf get() = this.dp2px.toFloat()
val Int.sp2px get() = ConvertUtils.sp2px(this.toFloat())
val Int.sp2pxf get() = this.sp2px.toFloat()

val Float.dp2px get() = ConvertUtils.dp2px(this)
val Float.dp2pxf get() = this.dp2px.toFloat()
val Float.sp2px get() = ConvertUtils.sp2px(this)
val Float.sp2pxf get() = this.sp2px.toFloat()

fun <BD : ViewDataBinding> ViewGroup.bindView(@LayoutRes layoutRes: Int): BD {
    return DataBindingUtil.inflate(LayoutInflater.from(context),layoutRes,this,true)
}


