package com.uragiristereo.serializednavigationextension.navigation.compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import com.uragiristereo.serializednavigationextension.runtime.NavRoute

interface NavRouteScope<T : NavRoute> {
    val navBackStackEntry: NavBackStackEntry

    @Composable
    fun rememberNavArgsOf(): T
}
