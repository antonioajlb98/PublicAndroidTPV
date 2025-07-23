package com.antonioajlb.ui.presentation.order

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import org.koin.androidx.compose.koinViewModel

@Composable
fun OrderScreen(
    orderViewModel: OrderViewModel = koinViewModel()
) {

     val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass


    LaunchedEffect(Unit) {
        orderViewModel.onEvent(OrderScreenEvent.GetAllProducts)
    }


}