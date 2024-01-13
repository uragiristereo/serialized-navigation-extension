package com.uragiristereo.serializednavigationextension

import android.app.Application
import com.uragiristereo.serializednavigationextension.runtime.installSerializer
import com.uragiristereo.serializednavigationextension.serializer.KotlinxSerializer

class SneApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        installSerializer(KotlinxSerializer())
    }
}
