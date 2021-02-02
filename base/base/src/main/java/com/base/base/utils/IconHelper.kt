package com.base.base.utils

import com.base.base.R
import kotlin.math.abs

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-17 10:33
 * @email zhou_android@163.com
 *
 *
 * Talk is cheap, Show me the code.
 */
object IconHelper {

    private val icons = arrayOf(
        R.drawable.ic_ahri,
        R.drawable.ic_ezreal,
        R.drawable.ic_garen,
        R.drawable.ic_jinx,
        R.drawable.ic_katarina,
        R.drawable.ic_lisin,
        R.drawable.ic_luxanna,
        R.drawable.ic_sona,
        R.drawable.ic_teemo,
        R.drawable.ic_tristana)

    fun randomIcon(id: Int): Int {
        return icons[abs(id) % 10]
    }
}

