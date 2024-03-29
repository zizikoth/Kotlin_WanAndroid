package com.frame.version

object Config {
    const val applicationId = "com.memo.core.wan"
    const val compileSdkVersion = 30
    const val buildToolsVersion = "30.0.2"
    const val minSdkVersion = 21
    const val targetSdkVersion = 30
    const val versionCode = 1000
    const val versionName = "1.0.0"
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
}

object Module {
    const val isModuleMainRunAlone = false
    const val isModuleHomeRunAlone = false
    const val isModuleProjectRunAlone = false
    const val isModuleSystemRunAlone = false
    const val isModuleMineRunAlone = true
}

private object Versions {
    const val TestJunit = "4.13.1"
    const val TestExtJunit = "1.1.2"
    const val TextEspresso = "3.3.0"
    const val AgentWeb = "4.1.4"
    const val AgentWebQueue = "v1.0.5"

    const val Proguard = "1.0.2"
    const val BoostMultiDex = "1.0.1"

    const val BannerViewPager = "3.5.0"
    const val Kotlin = "1.4.21"
    const val AppCompat = "1.2.0"
    const val ARouterApi = "1.5.0"
    const val Material = "1.2.1"
    const val AndroidUtilCode = "1.29.0"
    const val CoreKtx = "1.3.1"
    const val ConstraintLayout = "2.0.2"
    const val ViewPager2 = "1.0.0"
    const val FlexBox = "2.0.1"
    const val Coroutine = "1.3.3"
    const val Glide = "4.11.0"
    const val Gson = "2.8.6"
    const val BaseRecyclerViewAdapterHelper = "3.0.4"
    const val RecycleView = "1.0.0"
    const val RefreshLayout = "2.0.1"
    const val Lifecycle = "2.2.0"
    const val RxHttp = "2.5.1"
    const val RxHttpCoroutine = "2.0.1"
    const val OkHttp = "4.9.0"
    const val LoadStatus = "1.0.3"
    const val Dialog = "3.2.4"
    const val MagicIndicator = "1.7.0"
    const val Lottie = "3.5.0"
    const val SwipeLayout = "1.2.0"

    const val DoKit = "3.3.5"
    const val LeakCanary = "2.5"
    const val ARouterCompiler = "1.2.2"

}

object Dep {
    const val TestJunit = "junit:junit:${Versions.TestJunit}"
    const val TestAndroidJunit = "androidx.test.ext:junit:${Versions.TestExtJunit}"
    const val TestAndroidEspresso = "androidx.test.espresso:espresso-core:${Versions.TextEspresso}"

    const val AgentWeb = "com.just.agentweb:agentweb-androidx:${Versions.AgentWeb}"
    const val AgentWebQueue = "com.github.Justson:dispatch-queue:${Versions.AgentWebQueue}"
    const val Proguard = "com.blankj:free-proguard:${Versions.Proguard}"

    const val BoostMultiDex = "com.bytedance.boost_multidex:boost_multidex:${Versions.BoostMultiDex}"
    const val BannerViewPager = "com.github.zhpanvip:BannerViewPager:${Versions.BannerViewPager}"
    const val Kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.Kotlin}"
    const val AppCompat = "androidx.appcompat:appcompat:${Versions.AppCompat}"
    const val ARouter = "com.alibaba:arouter-api:${Versions.ARouterApi}"
    const val Material = "com.google.android.material:material:${Versions.Material}"
    const val AndroidUtilCode = "com.blankj:utilcodex:${Versions.AndroidUtilCode}"
    const val CoreKtx = "androidx.core:core-ktx:${Versions.CoreKtx}"
    const val ConstraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.ConstraintLayout}"
    const val ViewPager2 = "androidx.viewpager2:viewpager2:${Versions.ViewPager2}"
    const val FlexBox = "com.google.android:flexbox:${Versions.FlexBox}"
    const val Coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Coroutine}"
    const val Glide = "com.github.bumptech.glide:glide:${Versions.Glide}"
    const val GlideOkHttp = "com.github.bumptech.glide:okhttp3-integration:${Versions.Glide}"
    const val Gson = "com.google.code.gson:gson:${Versions.Gson}"
    const val BaseRecyclerViewAdapterHelper = "com.github.CymChad:BaseRecyclerViewAdapterHelper:${Versions.BaseRecyclerViewAdapterHelper}"
    const val RecyclerView = "androidx.recyclerview:recyclerview:${Versions.RecycleView}"
    const val RefreshLayout = "com.scwang.smart:refresh-layout-kernel:${Versions.RefreshLayout}"
    const val RefreshLayoutHeader = "com.scwang.smart:refresh-header-classics:${Versions.RefreshLayout}"
    const val RefreshLayoutFooter = "com.scwang.smart:refresh-footer-ball:${Versions.RefreshLayout}"
    const val LifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.Lifecycle}"
    const val LifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Lifecycle}"
    const val LifecycleLiveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.Lifecycle}"
    const val LifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Lifecycle}"
    const val RxHttp = "com.ljx.rxhttp:rxhttp:${Versions.RxHttp}"
    const val RxHttpCoroutine = "com.ljx.rxlife:rxlife-coroutine:${Versions.RxHttpCoroutine}"
    const val OkHttp = "com.squareup.okhttp3:okhttp:${Versions.OkHttp}"
    const val LoadStatus = "com.github.zizikoth:Status:${Versions.LoadStatus}"
    const val Dialog = "com.kongzue.dialog_v3:dialog::${Versions.Dialog}"
    const val MagicIndicator = "com.github.hackware1993:MagicIndicator:${Versions.MagicIndicator}"
    const val Lottie = "com.airbnb.android:lottie:${Versions.Lottie}"
    const val SwipeLayout = "com.daimajia.swipelayout:library:${Versions.SwipeLayout}@aar"

    // debugApi
    const val DoKitDebug = "com.didichuxing.doraemonkit:dokitx:${Versions.DoKit}"
    const val LeakCanaryDebug = "com.squareup.leakcanary:leakcanary-android:${Versions.LeakCanary}"

    // releaseApi
    const val DoKitRelease = "com.didichuxing.doraemonkit:dokitx-no-op:${Versions.DoKit}"

    // kapt
    const val GlideCompiler = "com.github.bumptech.glide:compiler:${Versions.Glide}"
    const val RxHttpCompiler = "com.ljx.rxhttp:rxhttp-compiler:${Versions.RxHttp}"
    const val ARouterCompiler = "com.alibaba:arouter-compiler:${Versions.ARouterCompiler}"


}



