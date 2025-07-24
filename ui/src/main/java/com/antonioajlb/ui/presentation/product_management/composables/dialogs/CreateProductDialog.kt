package com.antonioajlb.ui.presentation.product_management.composables.dialogs

import android.graphics.Outline
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateProductDialog(
    onDismissRequest:() -> Unit
) {

    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }

    BasicAlertDialog(
        modifier = Modifier
            .clip(RoundedCornerShape(24.dp))
            .background(MaterialTheme.colorScheme.onSurface)
            .padding(16.dp),
        properties = DialogProperties(
            dismissOnBackPress = true
        ),
        onDismissRequest = onDismissRequest
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Text(
                text = "Create Product",
                style = MaterialTheme.typography.titleMedium
            )
            OutlinedTextField(
                shape = RoundedCornerShape(16.dp),
                placeholder = {
                    Text(text = "Name")
                },
                value = name,
                onValueChange = {
                    name = it
                }
            )

            OutlinedTextField(
                shape = RoundedCornerShape(16.dp),
                placeholder = {
                    Text(text = "Price")
                },
                value = price,
                onValueChange = {
                    price = it
                }
            )
        }
    }
    
}