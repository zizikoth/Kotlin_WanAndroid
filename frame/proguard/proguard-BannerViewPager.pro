# https://github.com/zhpanvip/BannerViewPager/blob/master/app/proguard-rules.pro
-keep class * extends androidx.fragment.app.Fragment {
    public void setUserVisibleHint(boolean);
    public void onHiddenChanged(boolean);
    public void onResume();
    public void onPause();
}
-keep public class * extends android.app.Activity
-keep class com.example.zhpan.circleviewpager.bean.** { *; }
-keep class com.example.zhpan.circleviewpager.net.** { *; }
-keep class com.zhpan.idea.** { *; }

-keep class androidx.recyclerview.widget.**{*;}
-keep class androidx.viewpager2.widget.**{*;}
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}