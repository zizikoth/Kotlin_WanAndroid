package com.base.base.entity.zip

/**
 * title:合并数据
 * describe:
 *
 * @author zhou
 * @date 2021-01-26 21:02
 *
 * Talk is cheap, Show me the code.
 */

data class ZipData2<A, B>(var first: A, var second: B)
data class ZipData3<A, B, C>(var first: A, var second: B, var third: C)
data class ZipData4<A, B, C, D>(var first: A, var second: B, var third: C, var forth: D)
data class ZipData5<A, B, C, D, E>(
    var first: A,
    var second: B,
    var third: C,
    var forth: D,
    var fifth: E
)