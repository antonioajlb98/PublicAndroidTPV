package com.antonioajlb.ui.theme

import android.app.Activity
import android.graphics.Color.TRANSPARENT
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat


private val LightColorScheme = lightColorScheme(
    primary = Primary,
    secondary = Secondary,
    onPrimary = OnPrimary,
    onSecondary = OnSecondary,
    onSurface = OnSurface,

)

@Composable
fun QuickTPVTheme(
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.setDecorFitsSystemWindows(window, false)
            window.statusBarColor = TRANSPARENT
            window.navigationBarColor = TRANSPARENT
        }
    }

    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = AppTypography,
        content = content
    )
}