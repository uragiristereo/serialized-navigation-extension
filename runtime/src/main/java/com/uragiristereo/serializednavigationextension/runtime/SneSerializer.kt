package com.uragiristereo.serializednavigationextension.runtime

import kotlin.reflect.KClass

interface SneSerializer {
    fun <T : NavRoute> serialize(value: T, klass: KClass<T>): String

    fun <T : NavRoute> deserialize(value: String, klass: KClass<T>): T

    fun <T : NavRoute> registerPolymorphicType(klass: KClass<T>)
}

fun installSerializer(serializer: SneSerializer) {
    SneInstance.serializer = serializer
}
