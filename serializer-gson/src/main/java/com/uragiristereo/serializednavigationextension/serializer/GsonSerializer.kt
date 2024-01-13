package com.uragiristereo.serializednavigationextension.serializer

import com.google.gson.Gson
import com.uragiristereo.serializednavigationextension.runtime.NavRoute
import com.uragiristereo.serializednavigationextension.runtime.SneSerializer
import kotlin.reflect.KClass

class GsonSerializer(private val gson: Gson = Gson()) : SneSerializer {
    override fun <T : NavRoute> serialize(value: T, klass: KClass<T>): String {
        return gson.toJson(value)
    }

    override fun <T : NavRoute> deserialize(value: String, klass: KClass<T>): T {
        return gson.fromJson(value, klass.java)
    }

    override fun <T : NavRoute> registerPolymorphicType(klass: KClass<T>) {}
}
