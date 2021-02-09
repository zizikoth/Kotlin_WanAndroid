package com.base.base.entity.local

/**
 * 展示页面状态
 * @property isFirstLoad 是否是第一次加载
 * @property code 请求码
 */
data class UiStatus(val isFirstLoad: Boolean, val code: Int)