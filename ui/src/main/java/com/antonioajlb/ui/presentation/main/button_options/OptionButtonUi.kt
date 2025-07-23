package com.antonioajlb.ui.presentation.main.button_options

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class OptionButtonUi(
    @StringRes val labelText: Int,
    val icon: ImageVector? = null,
    val action: ActionType
)
