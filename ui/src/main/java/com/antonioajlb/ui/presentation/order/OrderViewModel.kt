package com.antonioajlb.ui.presentation.order

import android.bluetooth.BluetoothDevice
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.antonioajlb.domain.model.Product
import com.antonioajlb.domain.use_cases.product.GetAllProductUseCase
import com.antonioajlb.ui.core.BaseViewModel
import com.dantsu.escposprinter.EscPosPrinter
import com.dantsu.escposprinter.connection.bluetooth.BluetoothConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Locale

class OrderViewModel(
    private val getAllProductUseCase: GetAllProductUseCase
) : BaseViewModel<OrderScreenState>(OrderScreenState()) {

    val cart = mutableStateListOf<Product>()
    var totalPrice by mutableDoubleStateOf(0.0)
    var device by mutableStateOf<BluetoothDevice?>(null)

    fun onEvent(event: OrderScreenEvent) {
        when (event) {
            is OrderScreenEvent.GetAllProducts -> {
                launchInViewModelScope {
                    getAllProductUseCase().fold(
                        onSuccess = {
                            updateState {
                                copy(products = it)
                            }
                        },
                        onFailure = {
                            updateState {
                                copy(error = it.message)
                            }
                        }
                    )
                }
            }

            is OrderScreenEvent.AddProductToCart -> {
                val quantity = if (event.quantity != 1 && event.quantity != 0) event.quantity else 1
                val exist = cart.find { it.id == event.product.id }
                if (exist != null) {
                    val index = cart.indexOf(exist)
                    cart[index] = exist.copy(quantity = exist.quantity + quantity)
                } else {
                    cart.add(event.product.copy(quantity = quantity))
                }
                event.onAddProduct()
                updatePrice()
            }

            is OrderScreenEvent.DeleteProductFromCart -> {
                cart.remove(event.product)
                updatePrice()
            }

            is OrderScreenEvent.ClearCart -> {
                cart.clear()
                updatePrice()
            }

            is OrderScreenEvent.IncrementProductQuantity -> {
                val index = cart.indexOf(event.product)
                cart[index] = event.product.copy(quantity = event.product.quantity + 1)
                updatePrice()
            }

            is OrderScreenEvent.RemoveProductQuantity -> {
                if (event.product.quantity == 1) {
                    cart.remove(event.product)
                } else {
                    val index = cart.indexOf(event.product)
                    cart[index] = event.product.copy(quantity = event.product.quantity - 1)
                }
                updatePrice()
            }

            is OrderScreenEvent.PrintTicket -> {
                launchInViewModelScope(
                    onStart = {
                        updateState {
                            copy(loadingTicket = true)
                        }
                    },
                    onComplete = {
                        updateState {
                            copy(loadingTicket = false)
                        }
                    }
                ) {
                    withContext(Dispatchers.IO) {
                        try {
                            val connection = BluetoothConnection(device)
                            val printer = EscPosPrinter(connection, 203, 48f, 32)
                            printer.printFormattedText(generateTicket())
                            cart.clear()
                            updatePrice()
                        } catch (e: Exception) {
                            device = null
                            e.printStackTrace()
                            updateState {
                                copy(
                                    error = "Ha ocurrido un error al conectar la impresora por favor compruebela",
                                )
                            }
                        }
                    }
                }
            }

            is OrderScreenEvent.GetDevolution -> {
                updateState {
                    copy(devolution = totalPrice)
                }
            }

            is OrderScreenEvent.CancelDevolution -> {
                updateState {
                    copy(devolution = null)
                }
            }


            is OrderScreenEvent.ClearError -> {
                updateState {
                    copy(error = null)
                }
            }
        }
    }

    private fun generateTicket(): String {
        val builder = StringBuilder()
        builder.append("[C]<u><font size='big'>Churreria</font></u>\n")
        builder.append("[C]<u><font size='big'>HNOS LUQUE</font></u>\n")
        builder.append("[L]\n")

        var total = 0.0
        for (product in cart) {
            val linea = "[L]${product.quantity} x ${product.name} [R]" +
                    String.format(Locale.getDefault(), "%.2f", product.price * product.quantity)
            builder.append(linea + "\n")
            total += product.price * product.quantity
        }

        builder.append("[C]-----------------------------\n")
        builder.append("[R]TOTAL: ${"%.2f".format(total)}\n")
        builder.append("[L]\n[C]<font size='small'>Gracias por su compra</font>")

        return builder.toString()
    }

    private fun updatePrice() {
        totalPrice = cart.sumOf { it.price * it.quantity }
    }
}