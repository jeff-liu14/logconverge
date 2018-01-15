# logconverge

```
-keep class com.moment.logconverge.entity.**{*;}

-dontwarn com.moment.logconverge.entity.**

-keep class org.litepal.** {
    *;
}

-keep class * extends org.litepal.crud.DataSupport {
    *;
}
```
