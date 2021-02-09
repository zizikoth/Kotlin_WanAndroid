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

    val imageUrls = arrayListOf(
        "https://pic2.zhimg.com/v2-1d03e533a77f6bba10bac65930854304_r.jpg",
        "https://pic2.zhimg.com/v2-3c7503ec95586f355f3efed74703489c_r.jpg",
        "https://pic2.zhimg.com/v2-de4234e22d6efcd46d5893852e8436b0_r.jpg",
        "https://pic2.zhimg.com/v2-3745de080e0c8782f7716fbe8dbc66d7_r.jpg",
        "https://pic1.zhimg.com/v2-7a734d5e5d0555fc81b98a59ce059f27_r.jpg",
        "https://pic1.zhimg.com/v2-0ca985a5215ee8e2d99e9b8e06670269_r.jpg",
        "https://pic1.zhimg.com/v2-4f2d381edba21945bb2be41c267f24cf_r.jpg",
        "https://pic2.zhimg.com/v2-2000225810225791d517eae32276026d_r.jpg",
        "https://pic1.zhimg.com/v2-acc063a944128af3b5ad85510c4807af_r.jpg",
        "https://pic1.zhimg.com/v2-acc063a944128af3b5ad85510c4807af_r.jpg"
    )

    @JvmStatic
    fun randomIcon(id: Int): Int {
        if (id == 9527) return R.drawable.ic_lol
        return icons[abs(id) % 10]
    }

    @JvmStatic
    fun randomAvatar(id: Int): String {
        return imageUrls[abs(id) % 10]
    }

}

