package com.uragiristereo.serializednavigationextension.runtime

import androidx.navigation.NavBackStackEntry

inline fun <reified T : NavRoute> NavBackStackEntry.navArgsOf(): T? {
    val args = arguments?.getString("args") ?: return null

    return SneInstance.decode(args, T::class)
}

inline fun <reified T : NavRoute> NavBackStackEntry.navArgsOf(defaultValue: T): T {
    val args = arguments?.getString("args") ?: return defaultValue

    return runCatching<T> {
        SneInstance.decode(args, T::class)
    }.getOrDefault(defaultValue)
}
