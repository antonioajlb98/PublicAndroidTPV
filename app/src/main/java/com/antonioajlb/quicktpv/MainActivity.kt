package com.antonioajlb.quicktpv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.antonioajlb.ui.presentation.AppNavigation
import com.antonioajlb.ui.theme.QuickTPVTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuickTPVTheme {
                AppNavigation()
            }
        }
    }
}