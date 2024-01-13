package com.uragiristereo.serializednavigationextension.navigation.fragment

import androidx.fragment.app.Fragment
import com.uragiristereo.serializednavigationextension.runtime.NavRoute
import com.uragiristereo.serializednavigationextension.runtime.navArgsOf

inline fun <reified T : NavRoute> Fragment.navArgsOf(defaultValue: T? = null): Lazy<T> {
    return object : Lazy<T> {
        private var cached: T? = null

        override val value: T
            get() {
                var args = cached

                if (args == null) {
                    args = when {
                        defaultValue != null -> arguments.navArgsOf(defaultValue)
                        else -> arguments.navArgsOf()
                    }
                }

                return args!!
            }

        override fun isInitialized() = cached != null
    }
}
