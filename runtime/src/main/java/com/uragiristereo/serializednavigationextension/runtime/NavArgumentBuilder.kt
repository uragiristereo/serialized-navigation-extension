package com.uragiristereo.serializednavigationextension.runtime

import androidx.navigation.NavArgumentBuilder
import androidx.navigation.NavType

inline fun <reified T : NavRoute> NavArgumentBuilder.sneArgument(defaultValue: T?) {
    type = NavType.StringType
    nullable = true

    if (defaultValue != null) {
        this.defaultValue = SneInstance.serialize(defaultValue, T::class)
    }
}
