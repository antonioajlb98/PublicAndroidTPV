package com.antonioajlb.ui.presentation.order

import com.antonioajlb.domain.model.Category
import com.antonioajlb.domain.model.Product

data class OrderScreenState(
    val products: List<Product> = listOf(),
    val categories: List<Category> = listOf(),
    val loadingTicket: Boolean = false,
    val devolution: Double? = null,
    val error: String? = null,
)
