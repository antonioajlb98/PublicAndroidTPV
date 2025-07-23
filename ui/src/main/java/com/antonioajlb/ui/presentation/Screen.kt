package com.antonioajlb.ui.presentation

sealed class Screen(
    val route: String
) {

    data object Main: Screen("Main")
    data object Order: Screen("Order")
    data object ProductManagement: Screen("ProductManagement")

}