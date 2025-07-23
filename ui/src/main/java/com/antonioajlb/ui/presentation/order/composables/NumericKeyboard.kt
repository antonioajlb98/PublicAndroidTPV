package com.antonioajlb.ui.presentation.order.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NumericKeyboard(
    onNumberClick: (String) -> Unit,
    onBackspaceClick: () -> Unit,
    modifier: Modifier = Modifier,
    number: String
) {
    val keys = listOf(
        listOf(1, 2, 3),
        listOf(4, 5, 6),
        listOf(7, 8, 9),
        listOf(null, 0, "€")
    )

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .weight(.66f)
                    .fillMaxSize()
                    .clip(RoundedCornerShape(18.dp))
                    .background(Color.Black),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .basicMarquee(),
                    text = number,
                    color = Color.White,
                    fontSize = 24.sp,
                    maxLines = 1,
                )
            }
            Keyboard(
                key = "<-",
                modifier = Modifier
                    .weight(.33f)
                    .fillMaxSize()
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(18.dp)
                    )
                    .clip(RoundedCornerShape(18.dp))
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable { onBackspaceClick() }
            )
        }
        keys.forEach { row ->
            Row(
                modifier = Modifier
                    .weight(1f),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                row.forEach { key ->
                    if (key != null) {
                        Keyboard(
                            key = key.toString(),
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxSize()
                                .shadow(
                                    elevation = 8.dp,
                                    shape = RoundedCornerShape(18.dp)
                                )
                                .clip(RoundedCornerShape(18.dp))
                                .background(MaterialTheme.colorScheme.primary)
                                .clickable { onNumberClick(key.toString()) }
                        )

                    }
                }
            }
        }
    }
}