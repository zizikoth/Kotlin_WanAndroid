package com.base.base.config

import rxhttp.wrapper.annotation.DefaultDomain

enum class RunMode {
    // 本地     测试   预览      发布
    LocalTest, Debug, Preview, Release
}

object AppConfig {
    /*** 是否开启日志 ***/
    const val isOpenLog: Boolean = true

    /*** 打包运行模式 ***/
    var runMode: RunMode = RunMode.Release

    /*** 网络请求主地址 ***/
    @DefaultDomain
    @JvmField
    var baseUrl: String = when (runMode) {
        RunMode.LocalTest -> "https://www.wanandroid.com/"
        RunMode.Debug -> "https://www.wanandroid.com/"
        RunMode.Preview -> "https://www.wanandroid.com/"
        RunMode.Release -> "https://www.wanandroid.com/"
    }

}

object AppKeys {
    /*** 添加AppKey AppSecret ***/
}

