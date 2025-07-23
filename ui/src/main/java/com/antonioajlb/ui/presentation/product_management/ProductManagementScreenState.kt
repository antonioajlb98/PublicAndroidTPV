package com.antonioajlb.ui.presentation.product_management

import com.antonioajlb.domain.model.Category
import com.antonioajlb.domain.model.Product

data class ProductManagementScreenState(
    val products: List<Product> = listOf(),
    val categories: List<Category> = listOf(),
    val productToDelete: Product? = null,
    val productToUpdate: Product? = null
)
