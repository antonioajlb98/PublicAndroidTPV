package com.antonioajlb.ui.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.union
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.antonioajlb.ui.presentation.main.MainScreen
import com.antonioajlb.ui.presentation.order.OrderScreen
import com.antonioajlb.ui.presentation.product_management.ProductManagementScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold(
        contentWindowInsets = WindowInsets.systemBars
            .union(WindowInsets.displayCutout),
    ) { innerPadding ->
        NavHost(
            modifier = Modifier
                .background(
                    Brush.linearGradient(
                        listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.secondary,
                        )
                    )
                )
                .padding(innerPadding)
                .padding(
                    vertical = 8.dp,
                    horizontal = 16.dp
                ),
            navController = navController,
            startDestination = Screen.Main.route
        ) {
            composable(Screen.Main.route) {
                MainScreen(
                    navigateToOrderScreen = {
                        navController.navigate(Screen.Order.route)
                    },
                    navigateToSettingsScreen = {

                    },
                    navigateToProductManagementScreen = {
                        navController.navigate(Screen.ProductManagement.route)
                    },
                )
            }
            composable(Screen.Order.route) {
                OrderScreen()
            }

            composable(Screen.ProductManagement.route) {
                ProductManagementScreen()
            }
        }
    }
}