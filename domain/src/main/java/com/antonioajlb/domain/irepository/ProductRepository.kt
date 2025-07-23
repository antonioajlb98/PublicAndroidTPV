package com.antonioajlb.domain.irepository

import com.antonioajlb.domain.model.Product

interface ProductRepository {
    suspend fun getAllProducts(): Result<List<Product>>
    suspend fun getAllProductsWithCategory(): Result<List<Product>>
    suspend fun insertProduct(product: Product): Result<Long>
    suspend fun updateProduct(product: Product): Result<Unit>
    suspend fun deleteProduct(product: Product): Result<Unit>
}