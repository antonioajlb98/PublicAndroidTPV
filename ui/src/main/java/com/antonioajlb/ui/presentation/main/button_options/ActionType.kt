package com.antonioajlb.ui.presentation.main.button_options

sealed class ActionType {

    data object Order: ActionType()
    data object Settings: ActionType()
    data object ProductManagement: ActionType()

}