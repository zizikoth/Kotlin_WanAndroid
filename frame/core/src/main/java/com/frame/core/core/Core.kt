package com.frame.core.core

import java.lang.reflect.ParameterizedType

@Suppress("UNCHECKED_CAST")
fun <VM> getViewModelClass(clazz: Any): VM {
    return (clazz.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM
}