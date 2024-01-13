package com.uragiristereo.serializednavigationextension.navigation.fragment

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.NavGraphBuilder
import androidx.navigation.fragment.DialogFragmentNavigatorDestinationBuilder
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorDestinationBuilder
import androidx.navigation.fragment.dialog
import androidx.navigation.fragment.fragment
import androidx.navigation.get
import com.uragiristereo.serializednavigationextension.runtime.NavRoute
import com.uragiristereo.serializednavigationextension.runtime.SneInstance
import com.uragiristereo.serializednavigationextension.runtime.routeOf
import com.uragiristereo.serializednavigationextension.runtime.sneArgument
import kotlin.reflect.KClass

inline fun <reified F : Fragment, reified T : NavRoute> NavGraphBuilder.fragment(
    defaultValue: T? = null,
    builder: FragmentNavigatorDestinationBuilder.() -> Unit = {},
) {
    SneInstance.registerPolymorphicType(T::class)

    fragment<F>(
        route = routeOf<T>(),
        builder = {
            argument(name = "args") {
                sneArgument(defaultValue)
            }

            builder()
        },
    )
}

inline fun <reified T : NavRoute> NavGraphBuilder.fragment(
    fragmentClassName: String,
    defaultValue: T? = null,
    builder: FragmentNavigatorDestinationBuilder.() -> Unit = {},
) {
    SneInstance.registerPolymorphicType(T::class)

    @Suppress("UNCHECKED_CAST")
    destination(
        FragmentNavigatorDestinationBuilder(
            navigator = provider[FragmentNavigator::class],
            route = routeOf<T>(),
            fragmentClass = Class.forName(fragmentClassName).kotlin as KClass<Fragment>,
        ).apply {
            argument(name = "args") {
                sneArgument(defaultValue)
            }

            builder()
        }
    )
}

inline fun <reified DF : DialogFragment, reified T : NavRoute> NavGraphBuilder.dialog(
    defaultValue: T? = null,
    builder: DialogFragmentNavigatorDestinationBuilder.() -> Unit = {},
) {
    SneInstance.registerPolymorphicType(T::class)

    dialog<DF>(
        route = routeOf<T>(),
        builder = {
            argument(name = "args") {
                sneArgument(defaultValue)
            }

            builder()
        },
    )
}
