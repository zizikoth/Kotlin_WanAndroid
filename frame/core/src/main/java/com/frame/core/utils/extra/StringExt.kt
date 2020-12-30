@file:Suppress("DEPRECATION")

package com.frame.core.utils.extra

import android.graphics.Color
import android.text.Html
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import com.blankj.utilcode.util.EncryptUtils
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.*

/**
 * title:对于String的拓展
 * describe:
 *
 * @author zhou
 * @date 2019-01-29 14:32
 */

private const val httpRegex =
	"(((https|http)?://)?([a-z0-9]+[.])|(www.))\\w+[.|/]([a-z0-9]*)?[[.]([a-z0-9]{0,})]+((/[\\S&&[^,;\u4E00-\u9FA5]]+)+)?([.][a-z0-9]{0,}+|/?)"
private const val phoneRegex = "1\\d{10}$"
private const val emailRegex = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?"
private const val idCardRegex = "[1-9]\\d{16}[a-zA-Z0-9]"
private const val chineseRegex = "^[\u4E00-\u9FA5]+$"

/**
 * 是否是手机号
 * 1+后面10位
 */
fun String?.isPhone(): Boolean = this != null && phoneRegex.toRegex().matches(this.trim())

/**
 * 是否是邮箱地址
 */
fun String?.isEmail(): Boolean = this != null && emailRegex.toRegex().matches(this.trim())

/**
 * 是否是身份证号码
 */
fun String?.isIDCard(): Boolean = this != null && idCardRegex.toRegex().matches(this.trim())

/**
 * 是否是中文字符
 */
fun String?.isChinese(): Boolean = this != null && chineseRegex.toRegex().matches(this.trim())

/**
 * 判断字符串是否是网址
 */
fun String?.isHttpUrl(): Boolean = this != null && httpRegex.toRegex().matches(this.trim())

/**
 * 获取当前字符串的md5
 */
fun String.md5(): String = EncryptUtils.encryptMD5ToString(this)

/**
 * 判断字符串是否为空或者是null的任意变化
 */
fun String?.isNull() = isNullOrEmpty() || this.trim().toLowerCase(Locale.getDefault()) == "null"

/**
 * Html格式化
 */
fun String.fromHtml() =
	if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
		Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString()
	} else {
		Html.fromHtml(this).toString()
	}

/**
 * 保留两位小数
 */
fun Double.keep2Decimal(): String {
	val df = DecimalFormat()
	df.maximumFractionDigits = 2
	df.groupingSize = 0
	df.roundingMode = RoundingMode.FLOOR
	val style = "###0.00" // 定义要显示的数字的格式
	df.applyPattern(style)
	return df.format(this)
}

/**
 * 首航缩进
 */
fun String.firstLineIndent(): SpannableStringBuilder {
	val span = SpannableStringBuilder("缩进$this")
	span.setSpan(ForegroundColorSpan(Color.TRANSPARENT), 0, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
	return span
}
