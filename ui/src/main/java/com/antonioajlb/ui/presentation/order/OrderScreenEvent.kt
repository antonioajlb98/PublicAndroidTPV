package com.antonioajlb.ui.presentation.order

import com.antonioajlb.domain.model.Product

sealed interface OrderScreenEvent {

    data object GetAllProducts: OrderScreenEvent
    data class AddProductToCart(val product: Product, val quantity: Int,val onAddProduct: () -> Unit): OrderScreenEvent
    data class IncrementProductQuantity(val product: Product): OrderScreenEvent
    data class RemoveProductQuantity(val product: Product): OrderScreenEvent
    data class DeleteProductFromCart(val product: Product): OrderScreenEvent
    data object GetDevolution: OrderScreenEvent
    data object CancelDevolution: OrderScreenEvent
    data object PrintTicket: OrderScreenEvent
    data object ClearCart: OrderScreenEvent
    data object ClearError: OrderScreenEvent

}