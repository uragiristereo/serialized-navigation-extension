package com.uragiristereo.serializednavigationextension.navigation.compose

import androidx.compose.animation.AnimatedContentScope
import com.uragiristereo.serializednavigationextension.runtime.NavRoute

interface ComposableNavRouteScope<T : NavRoute> : NavRouteScope<T> {
    val animatedContentScope: AnimatedContentScope
}
