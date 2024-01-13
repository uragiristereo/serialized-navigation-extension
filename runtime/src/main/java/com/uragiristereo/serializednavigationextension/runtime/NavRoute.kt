package com.uragiristereo.serializednavigationextension.runtime

import android.os.Bundle
import androidx.core.os.bundleOf
import kotlin.reflect.KClass

interface NavRoute

inline val <T : NavRoute> KClass<T>.route: String
    get() {
        val prefix = "/"
        val args = "/{args}"

        val route = this.java.name
            .split('.')
            .last()
            .lowercase()
            .replaceFirst("route", "")
            .replace('$', '/')

        return prefix + route + args
    }

@get:JvmName("NavRouteRoute")
val NavRoute.route: String
    get() = this::class.route

inline val <reified T : NavRoute> T.route: String
    get() = routeOf<T>()

/**
 * Generate a String route from desired class/interface that implements NavRoute to be used in
 * one of the NavGraphBuilder extension functions. Note that the first **route** occurrence from the
 * class name will be omitted and `/{args}` suffix will always be generated.
 * ```
 * data class ProfileRoute(val profileId: Int) : NavRoute
 *
 * val route = routeOf<ProfileRoute>()
 * println(route) // prints /profile/{args}
 * ```
 * Generating from a sealed interface/class will also include both the sealed interface/class name
 * and the class name, useful for nested graphs.
 * ```
 * sealed interface SettingsRoute : NavRoute {
 *     data object Account : MyAppRoute
 * }
 *
 * val route = routeOf<SettingsRoute.Account>()
 * println(route) // prints /settings/account/{args}
 */
inline fun <reified T : NavRoute> routeOf(): String {
    return T::class.route
}

/**
 * Parse a class that implements NavRoute into String to be used when navigating using
 * `NavController.navigate()`.
 * ```
 * data class ProfileRoute(val profileId: Int) : NavRoute
 *
 * // Navigating into ProfileRoute with profileId = 1911 as the data
 * navController.navigate(
 *     route = ProfileRoute(profileId = 1911).parse()
 * )
 * ```
 */
inline fun <reified T : NavRoute> T.parse(): String {
    val encoded = SneInstance.encode(value = this, klass = T::class)

    return route.replaceFirst("{args}", encoded)
}

inline fun <reified T : NavRoute> T.toBundle(): Bundle {
    val serialized = SneInstance.serialize(this, T::class)

    return bundleOf("args" to serialized)
}
