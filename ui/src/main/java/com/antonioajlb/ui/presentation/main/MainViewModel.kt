package com.antonioajlb.ui.presentation.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.EditNote
import androidx.compose.material.icons.rounded.RestaurantMenu
import androidx.compose.material.icons.rounded.Settings
import androidx.lifecycle.ViewModel
import com.antonioajlb.ui.presentation.main.button_options.ActionType
import com.antonioajlb.ui.presentation.main.button_options.OptionButtonUi
import com.example.ui.R

class MainViewModel: ViewModel() {

    val optionButtons = listOf(
        OptionButtonUi(labelText = R.string.order,icon = Icons.Rounded.RestaurantMenu,action = ActionType.Order),
        OptionButtonUi(R.string.product_management, icon = Icons.Rounded.EditNote, action = ActionType.ProductManagement),
        OptionButtonUi(R.string.settings,icon = Icons.Rounded.Settings,action = ActionType.Settings),
    )

}