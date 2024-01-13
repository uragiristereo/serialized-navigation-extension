package com.uragiristereo.serializednavigationextension.runtime

import android.net.Uri
import androidx.navigation.navArgument
import kotlin.reflect.KClass

object SneInstance {
    internal lateinit var serializer: SneSerializer

    val navTypes = listOf(
        navArgument(name = "args") {
            sneArgument(defaultValue = null)
        },
    )

    fun <T : NavRoute> serialize(value: T, klass: KClass<T>): String {
        return serializer.serialize(value, klass)
    }

    fun <T : NavRoute> deserialize(value: String, klass: KClass<T>): T {
        return serializer.deserialize(value, klass)
    }

    fun <T : NavRoute> encode(value: T, klass: KClass<T>): String {
        val serialized = serialize(value, klass)

        return Uri.encode(serialized)
    }

    fun <T : NavRoute> decode(value: String, klass: KClass<T>): T {
        val decoded = Uri.decode(value)

        return deserialize(decoded, klass)
    }

    fun <T : NavRoute> registerPolymorphicType(klass: KClass<T>) {
        serializer.registerPolymorphicType(klass)
    }
}
