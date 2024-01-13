package com.uragiristereo.serializednavigationextension.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.uragiristereo.serializednavigationextension.SneRoute
import com.uragiristereo.serializednavigationextension.runtime.navArgsOf

class NotificationViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val args = savedStateHandle.navArgsOf<SneRoute.Notification>()
}
