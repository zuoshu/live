# This is a configuration file for ProGuard.
# http://proguard.sourceforge.net/index.html#manual/usage.html

# Optimizations: If you don't want to optimize, use the
# proguard-android.txt configuration file instead of this one, which
# turns off the optimization flags.  Adding optimization introduces
# certain risks, since for example not all optimizations performed by
# ProGuard works on all versions of Dalvik.  The following flags turn
# off various optimizations known to have issues, but the list may not
# be complete or up to date. (The "arithmetic" optimization can be
# used if you are only targeting Android 2.0 or later.)  Make sure you
# test thoroughly if you go this route.
-optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*
-optimizationpasses 5
-allowaccessmodification
-dontpreverify

# The remainder of this file is identical to the non-optimized version
# of the Proguard configuration file (except that the other file has
# flags to turn off optimization).
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose

# For Attributes
-keepattributes Signature
-keepattributes *Annotation*

# For R
-keep class **.R$* {*;}

# For Serializable
-keep class * implements java.io.Serializable {*;}

# For Parcelable
-keep class * implements android.os.Parcelable {*;}

# For Annotation
-keep class * extends java.lang.annotation.Annotation {*;}

# For FastJson
-dontwarn com.alibaba.fastjson.**
-keep class com.alibaba.fastjson.** {*;}

# For ButterKnife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

# For UmengTools
-dontwarn com.umeng.**
-keep class com.umeng.** {*;}

# For OkHttp & Picasso
-dontwarn okio.**
-dontwarn com.squareup.okhttp.**

# For Android Support
-dontwarn android.support.**

# For Apache Commons
-dontwarn org.apache.commons.**

#For debug
-renamesourcefileattribute unknown
-keepattributes SourceFile,LineNumberTable

# For Otto
-keepclassmembers class ** {
    @com.squareup.otto.Produce public *;
    @com.squareup.otto.Subscribe public *;
}

#For ZHConverter
-keep class com.spreada.utils.chinese.**

#For xUtils
-keep class com.lidroid.xutils.** {*;}


#umeng share lib
#-dontshrink
#-dontoptimize
-dontwarn com.google.android.maps.**
-dontwarn android.webkit.WebView
-dontwarn com.umeng.**
-dontwarn com.tencent.weibo.sdk.**
-dontwarn com.facebook.**

-keep enum com.facebook.**
-keepattributes Exceptions,InnerClasses,Signature
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable

-keep public interface com.facebook.**
-keep public interface com.tencent.**
-keep public interface com.umeng.socialize.**
-keep public interface com.umeng.socialize.sensor.**
-keep public interface com.umeng.scrshot.**

-keep public class com.umeng.socialize.* {*;}
-keep public class javax.**
-keep public class android.webkit.**

-keep class com.facebook.**
-keep class com.umeng.scrshot.**
-keep public class com.tencent.** {*;}
-keep class com.umeng.socialize.sensor.**

-keep class com.tencent.mm.sdk.modelmsg.WXMediaMessage {*;}

-keep class com.tencent.mm.sdk.modelmsg.** implements com.tencent.mm.sdk.modelmsg.WXMediaMessage$IMediaObject {*;}

-keep class im.yixin.sdk.api.YXMessage {*;}
-keep class im.yixin.sdk.api.** implements im.yixin.sdk.api.YXMessage$YXMessageData{*;}

-dontwarn com.xiaomi.push.**

-keep class com.baidu.**{ *; }

#webview js
-keepclassmembers class in.iqing.view.activity.PromotionActivity$JsOperation {
  public *;
}
-keepclassmembers class in.iqing.view.activity.WebActivity$JsOperation {
  public *;
}
-keepclassmembers class in.iqing.view.activity.OnlineGameActivity$JsOperation {
  public *;
}
-keepattributes *Annotation*
-keepattributes *JavascriptInterface*

-dontwarn android.net.SSLCertificateSocketFactory
-dontwarn android.app.Notification

#TODO ultimaterecyclerview should fix for support v23.2.1
-dontwarn com.marshalchen.ultimaterecyclerview.animators.BaseItemAnimator

#alipay
#-libraryjars libs/alipaySDK-20160223.jar

-keep class com.alipay.android.app.IAlixPay{*;}
-keep class com.alipay.android.app.IAlixPay$Stub{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback{*;}
-keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
-keep class com.alipay.sdk.app.PayTask{ public *;}
-keep class com.alipay.sdk.app.AuthTask{ public *;}