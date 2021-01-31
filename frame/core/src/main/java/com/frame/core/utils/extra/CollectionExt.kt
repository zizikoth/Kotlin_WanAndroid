package com.frame.core.utils.extra


fun <T> Collection<T>.toArrayList(): ArrayList<T> {
    return ArrayList(this)
}

fun <T> Collection<T>.convert2String(split: String = ","): String {
    val builder = StringBuilder("")
    this.forEachIndexed { index, t ->
        if (index == 0) {
            builder.append(t.toString())
        } else {
            builder.append(split).append(t.toString())
        }
    }
    return builder.toString()
}