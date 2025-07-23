package com.antonioajlb.domain.use_cases.product

import com.antonioajlb.domain.irepository.ProductRepository
import com.antonioajlb.domain.model.Product

class InsertProductUseCase(
    private val productRepository: ProductRepository
) {

    suspend operator fun invoke(product: Product): Result<Long> {
        return productRepository.insertProduct(product)
    }
}