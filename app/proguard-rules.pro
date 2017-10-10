# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\android-sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose
-ignorewarnings
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

-printseeds seeds.txt
-printusage unused.txt
-printmapping mapping.txt

#-keep class XXXX   保留类名不变，也就是类名不混淆，而类中的成员名不保证。当然也可以是继承XXX类的所有类名不混淆
#-keepclasseswithmembers class XXXX 保留类名和成员名,当然也可以是类中特定方法

#---------------------------------默认保留区-------------------------------
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class * extends java.lang.Throwable {*;}
-keep public class * extends java.lang.Exception {*;}
-keep public class com.android.vending.licensing.ILicensingService
-keep public class * extends android.support.**
-keep public class * extends android.app.Fragment
-dontwarn android.support.**
-keep class android.support.** {*;}

#报app:transformClassesAndResourcesWithProguardForAppRelease错加的
-dontwarn org.apache.http.**
-keep class org.apache.http.** {*;}

#自定义控件不要混淆
-keep public class * extends android.view.View {*;}
#adapter不能混淆
-keep public class * extends android.widget.BaseAdapter {*;}
#CusorAdapter不混淆
-keep public class * extends android.widget.CusorAdapter{*;}
#保护注解
-keepattributes *Annotation*
-keepattributes Permission


-keepclasseswithmembernames class * {
    native <methods>;
}
-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keep class * extends java.util.ListResourceBundle {
    protected Object[][] getContents();
}
-keepnames class * implements java.io.Serializable
-keepclassmembers class * implements java.io.Serializable {*;}

-keep class **.R$* {*;}
-keepclassmembers class * {
    void *(**On*Event);
}

#---------------------------------app其他内容------------------------------------
-keep class com.junwu.weather.entitys.**{*;}
#重复点击
-keepclasseswithmembernames class * {
    @SingleClick.* <methods>;
}
#权限申请
-keepclasseswithmembernames class * {
    @Permission.* <methods>;
}

-keep class com.junwu.aoplib.singleclick.*Aspect {*;}

##点击事件
#-keepclasseswithmembernames class * {
#    * * OnClick*(*);
#}

#---------------------------------webview------------------------------------
-keepclassmembers class fqcn.of.javascript.interface.for.Webview {
   public *;
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, jav.lang.String);
}
#html页面调用上传图片
-keepclassmembers class * extends android.webkit.WebChromeClient{
        public void openFileChooser(...);
}
#----------------------------------------------------------------------------




#bugly
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}

#bugly 版本升级
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}
-keep class android.support.**{*;}


#定位 高德
-keep class com.amap.api.location.**{*;}
-keep class com.amap.api.fence.**{*;}
-keep class com.autonavi.aps.amapapi.model.**{*;}

#*************EventBus*************#
-keep class de.greenrobot.event.**{*;}
-keepclassmembers class ** {
    public void onEvent*(**);
}

#***********Glide*****************#
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}

#*************butterknife**************#
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

#*************okhttp**************#
-keep class okhttp3.** { *;}
-keep interface okhttp3.* { *;}
-dontwarn okhttp3.**
-dontwarn okio.**
-keep class okio.**{*;}


#*************Dagger**************#

-keepclasseswithmembernames class * {
    @Provides.* <methods>;
}

#*************加载框动画**************#
-keep public class * extends com.wang.avi.Indicator
#-dontwarn com.wang.avi.indicators.**
#-keep public class com.wang.avi.indicators.**{*;}