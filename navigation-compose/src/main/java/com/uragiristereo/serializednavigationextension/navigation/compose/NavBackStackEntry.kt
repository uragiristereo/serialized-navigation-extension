package com.uragiristereo.serializednavigationextension.navigation.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavBackStackEntry
import com.uragiristereo.serializednavigationextension.runtime.NavRoute
import com.uragiristereo.serializednavigationextension.runtime.navArgsOf

/**
 * Retrieve the nav args from a class that implements `NavRoute` and remembers it. The class type
 * must match exactly with any Compose `NavGraphBuilder` route.
 *
 * Note that this overload is nullable since a data class route that being set as a `startDestination`
 * wouldn't have any data for the first time. Consider using another overload that takes default
 * value parameter to make it not nullable.
 */
@Composable
inline fun <reified T : NavRoute> NavBackStackEntry.rememberNavArgsOf(): T? {
    return remember(this) { navArgsOf() }
}

/**
 * Retrieve the nav args from a class routes that implements `NavRoute` and remembers it. The class
 * type must match exactly with any Compose `NavGraphBuilder` route.
 *
 * @param defaultValue the value that will be used if the args data is not set, usually happens
 * with the data class route that being set as a `startDestination` for the first time.
 * @return a class that implements `NavRoute`
 */
@Composable
inline fun <reified T : NavRoute> NavBackStackEntry.rememberNavArgsOf(defaultValue: T): T {
    return remember(this) { navArgsOf(defaultValue) }
}
