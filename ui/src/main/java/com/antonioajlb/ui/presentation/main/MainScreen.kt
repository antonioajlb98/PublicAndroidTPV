package com.antonioajlb.ui.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.antonioajlb.ui.presentation.main.button_options.ActionType
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = koinViewModel(),
    navigateToOrderScreen: () -> Unit,
    navigateToProductManagementScreen: () -> Unit,
    navigateToSettingsScreen: () -> Unit
) {

    val buttons = mainViewModel.optionButtons

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        buttons.forEach { button ->
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .border(
                        width = 2.dp,
                        shape = RoundedCornerShape(16.dp),
                        color = MaterialTheme.colorScheme.secondary
                    )
                    .weight(1f)
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.onSurface)
                    .clickable {
                        when (button.action) {
                            is ActionType.Order -> {
                                navigateToOrderScreen()
                            }

                            is ActionType.ProductManagement -> {
                                navigateToProductManagementScreen()
                            }

                            is ActionType.Settings -> {
                                navigateToSettingsScreen()
                            }
                        }
                    },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                button.icon?.let {
                    Icon(
                        modifier = Modifier
                            .size(48.dp),
                        imageVector = it,
                        contentDescription = null,
                    )
                }
                Text(
                    text = stringResource(button.labelText),
                    fontSize = 36.sp
                )
            }
        }
    }
}