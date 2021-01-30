package com.frame.core.utils.extra


fun <T> Collection<T>.toArrayList(): ArrayList<T> {
    return ArrayList(this)
}