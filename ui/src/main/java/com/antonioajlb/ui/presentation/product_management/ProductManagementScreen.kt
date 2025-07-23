package com.antonioajlb.ui.presentation.product_management

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.antonioajlb.ui.presentation.product_management.composables.dialogs.DeleteProductDialog
import com.antonioajlb.utils.DeviceConfiguration
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductManagementScreen(
    viewModel: ProductManagementViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val deviceController = DeviceConfiguration.fromWindowSizeClass(windowSizeClass)

    LaunchedEffect(Unit) {
        viewModel.onEvent(ProductManagementScreenEvent.GetAllProducts)
    }

    when (deviceController) {
        DeviceConfiguration.MOBILE_PORTRAIT -> TODO()
        DeviceConfiguration.MOBILE_LANDSCAPE -> TODO()
        DeviceConfiguration.TABLET_PORTRAIT -> TODO()
        DeviceConfiguration.TABLET_LANDSCAPE -> {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Scaffold(
                    modifier = Modifier
                        .weight(.3f)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(24.dp))
                        .background(MaterialTheme.colorScheme.onSurface)
                        .padding(
                            horizontal = 16.dp,
                            vertical = 12.dp
                        )
                        .consumeWindowInsets(WindowInsets.systemBars),
                    floatingActionButton = {
                        Icon(
                            modifier = Modifier,
                            imageVector = Icons.Rounded.Add,
                            contentDescription = null
                        )
                    },
                    topBar = {
                            Text(
                                modifier = Modifier,
                                style = MaterialTheme.typography.titleLarge,
                                text = "Categorias",
                            )
                    },
                    containerColor = Color.Transparent,
                    floatingActionButtonPosition = FabPosition.Center
                ) { innerPadding ->
                    LazyColumn(
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(top = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {

                    }
                }
                Column(
                    modifier = Modifier
                        .weight(.7f)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(24.dp))
                        .background(MaterialTheme.colorScheme.onSurface)
                        .padding(
                            horizontal = 16.dp,
                            vertical = 12.dp
                        )
                ) {
                    Text(
                        style = MaterialTheme.typography.titleLarge,
                        text = "Productos",
                    )

                    //Test Commit
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        items(state.products) {

                        }
                    }
                }
            }
        }

        DeviceConfiguration.DESKTOP -> TODO()

    }
    state.productToDelete?.let { product ->
        DeleteProductDialog(
            product = product,
            onConfirm = { viewModel.onEvent(ProductManagementScreenEvent.DeleteProduct(product)) },
            onDismissRequest = { viewModel.onEvent(ProductManagementScreenEvent.DismissDeleteProductConfirmation) }
        )
    }

}