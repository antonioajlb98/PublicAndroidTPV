package com.antonioajlb.domain.use_cases.product

import com.antonioajlb.domain.irepository.ProductRepository
import com.antonioajlb.domain.model.Product

class DeleteProductUseCase(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(product: Product): Result<Unit> {
        return productRepository.deleteProduct(product)
    }
}