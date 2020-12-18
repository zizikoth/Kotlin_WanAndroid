package com.frame.core.utils

import com.frame.core.utils.more.*
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-05-31 17:17
 */
object GsonHelper {

    private var gson: Gson? = null

    /**
     * 获取Gson
     * @return Gson
     */
    @JvmStatic
    fun getGson(): Gson {
        if (gson == null) {
            gson = GsonBuilder()
                // 如果有一些特定的类也是可以同样设置默认值
                .registerTypeAdapter(String::class.java, StringTypeAdapter())
                .registerTypeAdapter(Int::class.java, IntTypeAdapter())
                .registerTypeAdapter(Double::class.java, DoubleTypeAdapter())
                .registerTypeAdapter(Float::class.java, FloatTypeAdapter())
                .registerTypeAdapter(Long::class.java, LongTypeAdapter())
                .registerTypeAdapter(Boolean::class.java, BooleanTypeAdapter())
                // 日期格式化
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                // 防止Html乱码
                .disableHtmlEscaping()
                // 格式化结果
                .setPrettyPrinting()
                .create()
        }
        return gson!!
    }

    /**
     * 解析成字符串
     */
    @JvmStatic
    fun parse2Json(any: Any): String = getGson().toJson(any)

    /**
     * 解析实体类
     */
    @JvmStatic
    inline fun <reified T> parse2Bean(json: String): T = getGson().fromJson(json, T::class.java)

    /**
     * 解析列表
     */
    @JvmStatic
    inline fun <reified T> parse2List(json: String): ArrayList<T> =
        getGson().fromJson(json, TypeToken.getParameterized(ArrayList::class.java, T::class.java).type)
}
