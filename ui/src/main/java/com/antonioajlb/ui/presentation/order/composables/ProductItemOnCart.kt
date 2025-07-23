package com.antonioajlb.ui.presentation.order.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.antonioajlb.domain.model.Product
import java.util.Locale

@Composable
fun ProductItemOnCart(
    product: Product,
    onClickAddQuantity: () -> Unit = {},
    onClickRemoveQuantity: () -> Unit = {},
    onDeleteProduct: () -> Unit = {}
) {

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Red)
                .clickable {
                    onDeleteProduct()
                }
                .padding(8.dp),
            imageVector = Icons.Rounded.Delete,
            contentDescription = null,
            tint = Color.White
        )
        Row(
            modifier = Modifier
                .weight(.7f)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onPrimary,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = product.name,
                maxLines = 2
            )

            Text(
                modifier = Modifier,
                text = "${String.format(Locale.getDefault(), "%.2f", product.price * product.quantity)}€",
            )
        }
        Row(
            modifier = Modifier
                .weight(.3f)
                .border(width = 1.dp, color = MaterialTheme.colorScheme.onPrimary, shape = RoundedCornerShape(8.dp)),
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                modifier = Modifier
                    .weight(.33f)
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .clickable {
                        onClickRemoveQuantity()
                    }
                    .padding(8.dp),
                imageVector = Icons.Rounded.Remove,
                contentDescription = null,
                tint = Color.White
            )

            Text(
                modifier = Modifier
                    .weight(.33f),
                text = product.quantity.toString(),
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
            Icon(
                modifier = Modifier
                    .weight(.33f)
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .clickable {
                        onClickAddQuantity()
                    }
                    .padding(8.dp),
                imageVector = Icons.Rounded.Add,
                contentDescription = null,
                tint = Color.White
            )

        }
    }

}