# 指定外部模糊字典 proguard.txt 改为混淆文件名，下同
-obfuscationdictionary oO0〇8.txt
# 指定class模糊字典
-classobfuscationdictionary oO0〇8.txt
# 指定package模糊字典
-packageobfuscationdictionary oO0〇8.txt


# 保证Entity不被混淆
-keep class com.base.base.api.ApiResponse{*;}
-keep class com.base.base.entity.**{*;}