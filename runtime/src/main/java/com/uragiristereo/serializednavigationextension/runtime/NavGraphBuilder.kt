package com.uragiristereo.serializednavigationextension.runtime

import android.app.Activity
import androidx.navigation.ActivityNavigatorDestinationBuilder
import androidx.navigation.NavGraphBuilder
import androidx.navigation.activity
import androidx.navigation.navigation
import kotlin.reflect.KClass

inline fun <reified A : Activity, reified T : NavRoute> NavGraphBuilder.activity(
    defaultValue: T? = null,
    builder: ActivityNavigatorDestinationBuilder.() -> Unit = {},
) {
    SneInstance.registerPolymorphicType(T::class)

    activity(
        route = routeOf<T>(),
        builder = {
            argument(name = "args") {
                sneArgument(defaultValue)
            }

            activityClass = A::class

            builder()
        },
    )
}

inline fun <reified StartDestination : NavRoute, reified Route : NavRoute> NavGraphBuilder.navigation(
    startDestination: KClass<StartDestination>,
    route: KClass<Route>,
    builder: NavGraphBuilder.() -> Unit,
) {
    SneInstance.registerPolymorphicType(route)

    navigation(
        startDestination = startDestination.route,
        route = route.route,
        builder = builder,
    )
}
