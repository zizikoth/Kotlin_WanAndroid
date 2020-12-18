package com.frame.core.utils.extra

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import java.io.Serializable

/**
 * title:页面跳转
 * describe:
 *
 * @author zhou
 * @date 2019-03-22 11:20
 */
/**
 * Application启动Activity
 */
inline fun <reified T : Activity> Application.startActivity(vararg params: Pair<String, Any?>) {
	val intent = createIntent(this, T::class.java, params)
	intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
	this.startActivity(intent)
}

/**
 * 跳转Activity
 */
inline fun <reified T : Activity> Context.startActivity(vararg params: Pair<String, Any?>) =
	internalStartActivity(this, T::class.java, params)

/**
 * 跳转Activity并且请求结果
 */
inline fun <reified T : Activity> Activity.startActivityForResult(
	vararg params: Pair<String, Any?>,
	requestCode: Int
) =
	internalStartActivityForResult(this, T::class.java, params, requestCode)

/**
 * 关闭当前Activity并且附带结果
 */
fun Activity.finishActivityWithResult(vararg params: Pair<String, Any?>) {
	val intent = Intent()
	fillIntentArguments(intent, params)
	setResult(RESULT_OK, intent)
	finish()
}

/**
 * 跳转Activity
 */
inline fun <reified T : Activity> Fragment.startActivity(vararg params: Pair<String, Any?>) =
	internalStartActivity(activity!!, T::class.java, params)

/**
 * 跳转Activity并且请求结果
 */
inline fun <reified T : Activity> Fragment.startActivityForResult(
	vararg params: Pair<String, Any?>,
	requestCode: Int
) =
	internalStartActivityForResult(this, T::class.java, params, requestCode)

/**
 * 为Fragment添加参数
 */
fun <T : Fragment> T.withArguments(vararg params: Pair<String, Any?>): T {
	arguments = bundleOf(*params)
	return this
}


// ---------------------------------------- 一般来说不要使用下面的方法，可以对下面的方法进行一次封装来使用 ----------------------------------------

fun internalStartActivity(
	context: Context,
	activity: Class<out Activity>,
	params: Array<out Pair<String, Any?>>
) =
	context.startActivity(createIntent(context, activity, params))

fun internalStartActivityForResult(
	activity: Activity,
	clazz: Class<out Activity>,
	params: Array<out Pair<String, Any?>>,
	requestCode: Int
) =
	activity.startActivityForResult(createIntent(activity, clazz, params), requestCode)

fun internalStartActivityForResult(
	fragment: Fragment,
	clazz: Class<out Activity>,
	params: Array<out Pair<String, Any?>>,
	requestCode: Int
) =
	fragment.startActivityForResult(createIntent(fragment.requireActivity(), clazz, params), requestCode)

fun <T> createIntent(
	context: Context,
	clazz: Class<out T>,
	params: Array<out Pair<String, Any?>>
): Intent {
	val intent = Intent(context, clazz)
	if (params.isNotEmpty()) fillIntentArguments(intent, params)
	return intent
}

fun fillIntentArguments(intent: Intent, params: Array<out Pair<String, Any?>>) {
	params.forEach {
		when (val value = it.second) {
			null -> intent.putExtra(it.first, null as Serializable?)
			is Int -> intent.putExtra(it.first, value)
			is Long -> intent.putExtra(it.first, value)
			is CharSequence -> intent.putExtra(it.first, value)
			is String -> intent.putExtra(it.first, value)
			is Float -> intent.putExtra(it.first, value)
			is Double -> intent.putExtra(it.first, value)
			is Char -> intent.putExtra(it.first, value)
			is Short -> intent.putExtra(it.first, value)
			is Boolean -> intent.putExtra(it.first, value)
			is Serializable -> intent.putExtra(it.first, value)
			is Bundle -> intent.putExtra(it.first, value)
			is Parcelable -> intent.putExtra(it.first, value)
			is Array<*> -> when {
				value.isArrayOf<CharSequence>() -> intent.putExtra(it.first, value)
				value.isArrayOf<String>() -> intent.putExtra(it.first, value)
				value.isArrayOf<Parcelable>() -> intent.putExtra(it.first, value)
			}
			is IntArray -> intent.putExtra(it.first, value)
			is LongArray -> intent.putExtra(it.first, value)
			is FloatArray -> intent.putExtra(it.first, value)
			is DoubleArray -> intent.putExtra(it.first, value)
			is CharArray -> intent.putExtra(it.first, value)
			is ShortArray -> intent.putExtra(it.first, value)
			is BooleanArray -> intent.putExtra(it.first, value)
		}
	}
}