package com.uragiristereo.serializednavigationextension.runtime

import android.app.Activity

inline fun <reified T : NavRoute> Activity.navArgsOf(defaultValue: T? = null): Lazy<T> {
    return object : Lazy<T> {
        private var cached: T? = null

        override val value: T
            get() {
                var args = cached

                if (args == null) {
                    args = when {
                        defaultValue != null -> intent?.extras.navArgsOf(defaultValue)
                        else -> intent?.extras.navArgsOf()
                    }
                }

                return args!!
            }

        override fun isInitialized() = cached != null
    }
}
