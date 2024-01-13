package com.uragiristereo.serializednavigationextension.runtime

import android.os.Bundle

inline fun <reified T : NavRoute> Bundle?.navArgsOf(defaultValue: T): T {
    val args = this?.getString("args") ?: return defaultValue

    return SneInstance.deserialize(args, T::class)
}

inline fun <reified T : NavRoute> Bundle?.navArgsOf(): T? {
    val args = this?.getString("args") ?: return null

    return SneInstance.deserialize(args, T::class)
}
