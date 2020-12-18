# 自定义设置 主要是为了在Debug模式中使用混淆
# 由于DoraemonKit只在Debug模式中使用 在Release模式中为空实现 所以全部保留
-keep class com.didichuxing.doraemonkit.** { *; }