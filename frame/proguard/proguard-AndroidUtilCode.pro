# https://github.com/Blankj/AndroidUtilCode/blob/androidx/lib/utilcode/proguard-rules.pro

-dontwarn com.blankj.utilcode.**

-keepclassmembers class * {
    @com.blankj.utilcode.util.BusUtils$Bus <methods>;
}

-keep public class * extends com.blankj.utilcode.util.ApiUtils$BaseApi
-keep,allowobfuscation @interface com.blankj.utilcode.util.ApiUtils$Api
-keep @com.blankj.utilcode.util.ApiUtils$Api class *

-keepattributes *Annotation*