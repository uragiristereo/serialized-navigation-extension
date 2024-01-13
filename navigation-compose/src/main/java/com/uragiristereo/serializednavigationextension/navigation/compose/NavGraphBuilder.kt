package com.uragiristereo.serializednavigationextension.navigation.compose

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.navigation
import com.uragiristereo.serializednavigationextension.runtime.NavRoute
import com.uragiristereo.serializednavigationextension.runtime.SneInstance
import com.uragiristereo.serializednavigationextension.runtime.route
import com.uragiristereo.serializednavigationextension.runtime.routeOf
import kotlin.reflect.KClass

inline fun <reified T : NavRoute> NavGraphBuilder.composable(
    defaultValue: T,
    deepLinks: List<NavDeepLink> = emptyList(),
    noinline enterTransition: (@JvmSuppressWildcards
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = null,
    noinline exitTransition: (@JvmSuppressWildcards
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = null,
    noinline popEnterTransition: (@JvmSuppressWildcards
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? =
        enterTransition,
    noinline popExitTransition: (@JvmSuppressWildcards
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? =
        exitTransition,
    crossinline content: @Composable ComposableNavRouteScope<T>.() -> Unit,
) {
    SneInstance.registerPolymorphicType(T::class)

    composable(
        route = routeOf<T>(),
        arguments = SneInstance.navTypes,
        deepLinks = deepLinks,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
        content = { navBackStackEntry ->
            val animatedContentScope = this

            val scope = object : ComposableNavRouteScope<T> {
                override val animatedContentScope = animatedContentScope
                override val navBackStackEntry = navBackStackEntry

                @Composable
                override fun rememberNavArgsOf(): T {
                    return navBackStackEntry.rememberNavArgsOf(defaultValue)
                }
            }

            content(scope)
        },
    )
}

inline fun <reified T : NavRoute> NavGraphBuilder.composable(
    deepLinks: List<NavDeepLink> = emptyList(),
    noinline enterTransition: (@JvmSuppressWildcards
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = null,
    noinline exitTransition: (@JvmSuppressWildcards
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = null,
    noinline popEnterTransition: (@JvmSuppressWildcards
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? =
        enterTransition,
    noinline popExitTransition: (@JvmSuppressWildcards
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? =
        exitTransition,
    crossinline content: @Composable NullableComposableNavRouteScope<T?>.() -> Unit,
) {
    SneInstance.registerPolymorphicType(T::class)

    composable(
        route = routeOf<T>(),
        arguments = SneInstance.navTypes,
        deepLinks = deepLinks,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
        content = { navBackStackEntry ->
            val animatedContentScope = this

            val scope = object : NullableComposableNavRouteScope<T?> {
                override val animatedContentScope = animatedContentScope
                override val navBackStackEntry = navBackStackEntry

                @Composable
                override fun rememberNavArgsOf(): T? {
                    return navBackStackEntry.rememberNavArgsOf()
                }
            }

            content(scope)
        },
    )
}

inline fun <reified T : NavRoute> NavGraphBuilder.dialog(
    defaultValue: T,
    deepLinks: List<NavDeepLink> = emptyList(),
    dialogProperties: DialogProperties = DialogProperties(),
    crossinline content: @Composable NavRouteScope<T>.() -> Unit,
) {
    SneInstance.registerPolymorphicType(T::class)

    dialog(
        route = routeOf<T>(),
        deepLinks = deepLinks,
        dialogProperties = dialogProperties,
        content = { navBackStackEntry ->
            val scope = object : NavRouteScope<T> {
                override val navBackStackEntry = navBackStackEntry

                @Composable
                override fun rememberNavArgsOf(): T {
                    return navBackStackEntry.rememberNavArgsOf(defaultValue)
                }
            }

            content(scope)
        },
    )
}

inline fun <reified T : NavRoute> NavGraphBuilder.dialog(
    deepLinks: List<NavDeepLink> = emptyList(),
    dialogProperties: DialogProperties = DialogProperties(),
    crossinline content: @Composable NullableNavRouteScope<T?>.() -> Unit,
) {
    SneInstance.registerPolymorphicType(T::class)

    dialog(
        route = routeOf<T>(),
        deepLinks = deepLinks,
        dialogProperties = dialogProperties,
        content = { navBackStackEntry ->
            val scope = object : NullableNavRouteScope<T?> {
                override val navBackStackEntry = navBackStackEntry

                @Composable
                override fun rememberNavArgsOf(): T? {
                    return navBackStackEntry.rememberNavArgsOf()
                }
            }

            content(scope)
        },
    )
}

inline fun <reified StartDestination : NavRoute, reified Route : NavRoute> NavGraphBuilder.navigation(
    startDestination: KClass<StartDestination>,
    route: KClass<Route>,
    deepLinks: List<NavDeepLink> = emptyList(),
    noinline enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = null,
    noinline exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = null,
    noinline popEnterTransition: (
    AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?
    )? = enterTransition,
    noinline popExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = exitTransition,
    noinline builder: NavGraphBuilder.() -> Unit
) {
    SneInstance.registerPolymorphicType(route)

    navigation(
        startDestination = startDestination.route,
        route = route.route,
        arguments = SneInstance.navTypes,
        deepLinks = deepLinks,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
        builder = builder,
    )
}
