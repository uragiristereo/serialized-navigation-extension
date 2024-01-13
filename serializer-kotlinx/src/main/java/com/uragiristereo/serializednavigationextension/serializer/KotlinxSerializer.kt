package com.uragiristereo.serializednavigationextension.serializer

import com.uragiristereo.serializednavigationextension.runtime.NavRoute
import com.uragiristereo.serializednavigationextension.runtime.SneSerializer
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.PolymorphicModuleBuilder
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.serializer
import kotlin.reflect.KClass

class KotlinxSerializer(private val json: Json = Json) : SneSerializer {
    private val navRoutes = mutableMapOf<String, PolymorphicModuleBuilder<NavRoute>.() -> Unit>()
    private var _json = json

    @OptIn(InternalSerializationApi::class)
    override fun <T : NavRoute> serialize(value: T, klass: KClass<T>): String {
        return _json.encodeToString(klass.serializer(), value)
    }

    @OptIn(InternalSerializationApi::class)
    override fun <T : NavRoute> deserialize(value: String, klass: KClass<T>): T {
        return _json.decodeFromString(klass.serializer(), value)
    }

    @OptIn(InternalSerializationApi::class)
    override fun <T : NavRoute> registerPolymorphicType(klass: KClass<T>) {
        val fqcn = klass.java.name

        if (fqcn !in navRoutes) {
            navRoutes[fqcn] = {
                subclass(klass, klass.serializer())
            }
        }

        updateJson()
    }

    private fun updateJson() {
        _json = Json(from = json) {
            serializersModule = SerializersModule {
                polymorphic(NavRoute::class) {
                    navRoutes.forEach { (_, builder) ->
                        builder()
                    }
                }
            }
        }
    }
}
