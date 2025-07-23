package com.antonioajlb.domain.use_cases.product

import com.antonioajlb.domain.irepository.ProductRepository
import com.antonioajlb.domain.model.Product

class GetAllProductUseCase(
    private val productRepository: ProductRepository
) {
    suspend  operator fun invoke(): Result<List<Product>>{
        return productRepository.getAllProducts()
    }
}