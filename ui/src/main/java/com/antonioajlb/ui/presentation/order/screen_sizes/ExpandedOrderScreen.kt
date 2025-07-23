package com.antonioajlb.ui.presentation.order.screen_sizes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Calculate
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.antonioajlb.domain.model.Category
import com.antonioajlb.ui.presentation.composable.IconButton
import com.antonioajlb.ui.presentation.composable.PrimaryButton
import com.antonioajlb.ui.presentation.order.OrderScreenEvent
import com.antonioajlb.ui.presentation.order.OrderViewModel
import com.antonioajlb.ui.presentation.order.composables.GetDevolutionModal
import com.antonioajlb.ui.presentation.order.composables.NumericKeyboard
import com.antonioajlb.ui.presentation.order.composables.ProductItem
import com.antonioajlb.ui.presentation.order.composables.ProductItemOnCart
import com.antonioajlb.ui.presentation.order.composables.SelectPrinterDeviceModal
import com.example.ui.R
import java.util.Locale

@Composable
fun ExpandedOrderScreen(
    orderViewModel: OrderViewModel
) {

    var number by remember {
        mutableStateOf("")
    }

    var categorySelected by remember {
        mutableStateOf<Category?>(null)
    }
    var continueWithoutTickets by remember {
        mutableStateOf(false)
    }

    val state by orderViewModel.uiState.collectAsStateWithLifecycle()

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(.6f)
        ) {
            LazyRow(
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.categories) { category ->
                    Text(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (categorySelected == category) MaterialTheme.colorScheme.primary else Color.White)
                            .clickable {
                                categorySelected = category
                            }
                            .padding(8.dp),
                        text = category.name
                    )
                }
            }
            LazyVerticalGrid(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp)),
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.products.filter { it.category == categorySelected }) {
                    ProductItem(
                        product = it,
                        onClick = {
                            orderViewModel.onEvent(
                                if (number.contains("€")){
                                    OrderScreenEvent.AddProductToCart(
                                        product = it.copy(price = number.dropLast(1).toDouble()),
                                        quantity = 1,
                                        onAddProduct = { number = "" }
                                    )
                                }else{
                                    OrderScreenEvent.AddProductToCart(
                                        product = it,
                                        quantity = if (number.isEmpty()) 1 else number.toInt(),
                                        onAddProduct = { number = "" }
                                    )
                                }
                            )
                        }
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .weight(.4f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            NumericKeyboard(
                modifier = Modifier
                    .weight(.5f)
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp))
                    .padding(8.dp),
                onBackspaceClick = {
                    number = number.dropLast(1)
                },
                onNumberClick = {
                    if(!number.contains("€")){
                        number += it
                    }
                },
                number = number
            )

            LazyColumn(
                modifier = Modifier
                    .weight(.4f)
                    .fillMaxSize()
                    .clip(RoundedCornerShape(18.dp))
                    .background(Color.White),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(orderViewModel.cart) {
                    ProductItemOnCart(
                        product = it,
                        onClickAddQuantity = {
                            orderViewModel.onEvent(OrderScreenEvent.IncrementProductQuantity(it))
                        },
                        onClickRemoveQuantity = {
                            orderViewModel.onEvent(OrderScreenEvent.RemoveProductQuantity(it))
                        },
                        onDeleteProduct = {
                            orderViewModel.onEvent(OrderScreenEvent.DeleteProductFromCart(it))
                        }
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.total_price),
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )

                Text(
                    text = String.format(
                        locale = Locale.getDefault(),
                        format = "%.2f",
                        orderViewModel.totalPrice
                    ),
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )


            }
            Row(
                modifier = Modifier
                    .weight(.1f)
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                PrimaryButton(
                    modifier = Modifier
                        .weight(.3f),
                    text = stringResource(R.string.clear_cart),
                    color = Color.Red,
                    onClick = {
                        orderViewModel.onEvent(OrderScreenEvent.ClearCart)
                    }
                )
                PrimaryButton(
                    enabled = !state.loadingTicket && orderViewModel.device != null && orderViewModel.cart.isNotEmpty(),
                    modifier = Modifier
                        .weight(.5f),
                    text = stringResource(R.string.print_ticket),
                    color = MaterialTheme.colorScheme.onPrimary,
                    onClick = {
                        orderViewModel.onEvent(OrderScreenEvent.PrintTicket)
                    }
                )
                IconButton(
                    modifier = Modifier
                        .weight(.2f),
                    color = MaterialTheme.colorScheme.onPrimary,
                    icon = Icons.Rounded.Calculate,
                    onClick = {
                        orderViewModel.onEvent(OrderScreenEvent.GetDevolution)
                    }
                )
            }
        }
    }

    state.devolution?.let {
        GetDevolutionModal(
            price = it,
            onDismissRequest = {
                orderViewModel.onEvent(OrderScreenEvent.CancelDevolution)
            }
        )
    }




    state.error?.let {
        AlertDialog(
            onDismissRequest = { orderViewModel.onEvent(OrderScreenEvent.ClearError) },
            title = { Text(stringResource(R.string.error)) },
            text = { Text(text = it) },
            confirmButton = {
                TextButton(onClick = {
                    orderViewModel.onEvent(OrderScreenEvent.ClearError)
                }) {
                    Text(stringResource(R.string.accept))
                }
            }
        )
    }

    if (state.loadingTicket) {
        Dialog(
            onDismissRequest = {  }
        ) {
            Column(
                modifier = Modifier
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(stringResource(R.string.generating_ticket))
                CircularProgressIndicator()
            }
        }
    }



    if (orderViewModel.device == null && !continueWithoutTickets && state.error == null) {
        SelectPrinterDeviceModal(
            onDeviceSelected = {
                orderViewModel.device = it
            },
            onDismissRequest = {
                continueWithoutTickets = true
            }
        )
    }
}




