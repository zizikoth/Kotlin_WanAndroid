package com.frame.core.utils.extra

import androidx.annotation.IdRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.blankj.utilcode.util.ConvertUtils
import com.frame.core.core.CoreApp

/**
 * title: 获取资源文件
 * describe:
 *
 * @author zhou
 * @date 2019-01-29 14:43
 */

fun color(id: Int): Int = ContextCompat.getColor(CoreApp.app.applicationContext, id)

fun string(id: Int): String = CoreApp.app.applicationContext.resources.getString(id)

fun stringArray(id: Int): Array<String> = CoreApp.app.applicationContext.resources.getStringArray(id)

fun drawable(id: Int) = ContextCompat.getDrawable(CoreApp.app.applicationContext, id)

fun dimen(id: Int) = CoreApp.app.applicationContext.resources.getDimension(id)

val Int.dp2px get() = ConvertUtils.dp2px(this.toFloat())
val Float.dp2px get() = ConvertUtils.dp2px(this)
val Int.sp2px get() = ConvertUtils.sp2px(this.toFloat())
val Float.sp2px get() = ConvertUtils.sp2px(this)


fun FragmentActivity.showFragment(@IdRes container: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .add(container, fragment)
        .commit()
}

fun Fragment.showFragment(@IdRes container: Int, fragment: Fragment) {
    childFragmentManager.beginTransaction()
        .add(container, fragment)
        .commit()
}



