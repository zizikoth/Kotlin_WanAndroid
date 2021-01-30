package com.base.base.config

import rxhttp.wrapper.annotation.DefaultDomain

enum class RunMode {
    //测试    预览    发布
    Debug, Preview, Release
}

object AppConfig {
    /*** 是否开启日志 ***/
    const val isOpenLog: Boolean = true

    /*** 打包运行模式 ***/
    var runMode: RunMode = RunMode.Debug

    /*** 网络请求主地址 ***/
    @DefaultDomain
    @JvmField
    var baseUrl: String = when (runMode) {
        RunMode.Debug -> "https://wanandroid.com/"
        RunMode.Preview -> "https://wanandroid.com/"
        RunMode.Release -> "https://wanandroid.com/"
    }
}

object AppKeys {
    /*** 添加AppKey AppSecret ***/
}

