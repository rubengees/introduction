-keep class android.support.** { *; }
-keep interface android.support.** { *; }

-keepattributes EnclosingMethod

-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}