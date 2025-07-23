package com.antonioajlb.domain.model

data class Product(
    val id: Long = 0,
    val name: String,
    val price: Double,
    val quantity: Int = 1,
    val image: String? = null,
    val category: Category? = null
)
