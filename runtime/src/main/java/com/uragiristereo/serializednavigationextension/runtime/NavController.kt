package com.uragiristereo.serializednavigationextension.runtime

import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.Navigator
import androidx.navigation.createGraph
import androidx.navigation.navOptions
import kotlin.reflect.KClass

inline fun <reified T : NavRoute> NavController.navigate(
    route: T,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null,
) {
    navigate(
        route = route.parse(),
        navOptions = navOptions,
        navigatorExtras = navigatorExtras,
    )
}

fun NavController.navigate(
    route: NavRoute,
    builder: NavOptionsBuilder.() -> Unit,
) {
    navigate(
        route = route,
        navOptions = navOptions(builder),
    )
}

inline fun <reified StartDestination : NavRoute, reified Route : NavRoute> NavController.createGraph(
    startDestination: KClass<StartDestination>,
    route: KClass<Route>,
    builder: NavGraphBuilder.() -> Unit,
): NavGraph {
    return createGraph(
        startDestination = startDestination.route,
        route = route.route,
        builder = builder,
    )
}

inline fun <reified StartDestination : NavRoute> NavController.createGraph(
    startDestination: KClass<StartDestination>,
    builder: NavGraphBuilder.() -> Unit,
): NavGraph {
    return createGraph(
        startDestination = startDestination.route,
        route = null,
        builder = builder,
    )
}
