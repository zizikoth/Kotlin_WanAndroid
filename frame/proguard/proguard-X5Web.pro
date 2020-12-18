# https://x5.tencent.com/docs/access.html
-dontwarn dalvik.**
-dontwarn com.tencent.smtt.**

-keep class com.tencent.smtt.** {
    *;
}

-keep class com.tencent.tbs.** {
    *;
}

-keepattributes *Annotation*
-keepattributes *JavascriptInterface*
-keepclassmembers class * {
   @android.webkit.JavascriptInterface <methods>;
}