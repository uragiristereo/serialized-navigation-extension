-keep,includedescriptorclasses class * implements com.uragiristereo.serializednavigationextension.runtime.NavRoute { *; }

-keepclassmembers class * implements com.uragiristereo.serializednavigationextension.runtime.NavRoute {
    *** Companion;
}

-keepclasseswithmembers class * implements com.uragiristereo.serializednavigationextension.runtime.NavRoute {
    kotlinx.serialization.KSerializer serializer(...);
}
