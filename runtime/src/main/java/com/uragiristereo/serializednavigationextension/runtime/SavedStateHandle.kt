package com.uragiristereo.serializednavigationextension.runtime

import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

inline fun <reified T : NavRoute> SavedStateHandle.navArgsOf(): T? {
    val args = get<String>(key = "args") ?: return null

    return SneInstance.decode(args, T::class)
}

inline fun <reified T : NavRoute> SavedStateHandle.navArgsOf(defaultValue: T): T {
    val args = get<String>(key = "args") ?: return defaultValue

    return runCatching {
        SneInstance.decode(args, T::class)
    }.getOrDefault(defaultValue)
}

inline fun <reified T : NavRoute> SavedStateHandle.navArgsFlowOf(initialValue: T): Flow<T> {
    return getStateFlow(
        key = "args",
        initialValue = SneInstance.encode(initialValue, T::class),
    ).map { args ->
        SneInstance.decode(args, T::class)
    }
}

inline fun <reified T : NavRoute> SavedStateHandle.navArgsFlowOf(): Flow<T?> {
    return getStateFlow<String?>(
        key = "args",
        initialValue = null,
    ).map { args ->
        if (args != null) {
            SneInstance.decode(args, T::class)
        } else {
            null
        }
    }
}

inline fun <reified T : NavRoute> SavedStateHandle.updateNavArgs(value: T) {
    this["args"] = SneInstance.encode(value, T::class)
}
