package com.antonioajlb.ui.presentation.product_management

import com.antonioajlb.domain.model.Product

sealed interface ProductManagementScreenEvent {

    data object GetAllProducts: ProductManagementScreenEvent
    data class CreateProduct(val product: Product): ProductManagementScreenEvent
    data class RequestDeleteProduct(val product: Product): ProductManagementScreenEvent
    data object DismissDeleteProductConfirmation: ProductManagementScreenEvent
    data class DeleteProduct(val product: Product): ProductManagementScreenEvent
    data class RequestUpdateProduct(val product: Product): ProductManagementScreenEvent
    data class DismissUpdateProduct(val product: Product): ProductManagementScreenEvent
    data class UpdateProduct(val product: Product): ProductManagementScreenEvent
    data object OpenCreateProductDialog: ProductManagementScreenEvent
    data object CloseCreateProductDialog: ProductManagementScreenEvent

}