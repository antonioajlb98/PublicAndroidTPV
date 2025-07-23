package com.antonioajlb.ui.presentation.order.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.ui.R
import java.util.Locale

@Composable
fun GetDevolutionModal(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    price: Double
) {

    var money by remember {
        mutableStateOf("")
    }


    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .border(width = 3.dp, color = Color.Transparent, shape = RoundedCornerShape(8.dp))
                .background(Color.White)
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "${
                    stringResource(
                        R.string.price_to_pay,
                        String.format(Locale.getDefault(), "%.2f", price)
                    )
                }€",
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )

            TextField(
                value = money,
                onValueChange = { money = it },
                label = { Text(stringResource(R.string.money_received)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            if (money.isNotEmpty()) {
                val devolution =
                    String.format(Locale.getDefault(), "%.2f", money.toDouble() - price)
                Text(
                    text = "${stringResource(R.string.have_to_give, devolution)}€",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
            }
        }
    }
}