package com.uragiristereo.serializednavigationextension.navigation.compose

import androidx.compose.animation.AnimatedContentScope
import com.uragiristereo.serializednavigationextension.runtime.NavRoute

interface NullableComposableNavRouteScope<T : NavRoute?> : NullableNavRouteScope<T?> {
    val animatedContentScope: AnimatedContentScope
}
