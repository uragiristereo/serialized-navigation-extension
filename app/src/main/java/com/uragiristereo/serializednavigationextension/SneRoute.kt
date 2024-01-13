package com.uragiristereo.serializednavigationextension

import com.uragiristereo.serializednavigationextension.runtime.NavRoute
import kotlinx.serialization.Serializable

sealed interface SneRoute : NavRoute {
    @Serializable
    data object Home : SneRoute

    @Serializable
    data class Notification(val message: String) : SneRoute
}
