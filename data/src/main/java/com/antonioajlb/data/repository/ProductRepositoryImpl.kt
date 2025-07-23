package com.antonioajlb.data.repository

import com.antonioajlb.data.local.dao.ProductDao
import com.antonioajlb.data.mapper.toDomain
import com.antonioajlb.data.mapper.toEntity
import com.antonioajlb.domain.irepository.ProductRepository
import com.antonioajlb.domain.model.Product

class ProductRepositoryImpl(
    private val productDao: ProductDao
): ProductRepository {

    override suspend fun getAllProducts(): Result<List<Product>> {
        return try {
            Result.success(productDao.getAllProducts().map { it.toDomain() })
        }catch (ex: Exception){
            Result.failure(ex)
        }
    }

    override suspend fun getAllProductsWithCategory(): Result<List<Product>> {
        return try {
            Result.success(productDao.getAllProductsWithCategory().map { it.toDomain() })
        }catch (ex: Exception){
            Result.failure(ex)
        }
    }

    override suspend fun insertProduct(product: Product): Result<Long> {
        return try {
            Result.success(productDao.insertProduct(product.toEntity()))
        }catch (ex: Exception){
            Result.failure(ex)
        }
    }

    override suspend fun updateProduct(product: Product): Result<Unit> {
        return try {
            productDao.updateProduct(product.toEntity())
            Result.success(Unit)
        }catch (ex: Exception){
            Result.failure(ex)
        }
    }

    override suspend fun deleteProduct(product: Product): Result<Unit> {
        return try {
            productDao.deleteProduct(product.toEntity())
            Result.success(Unit)
        }catch (ex: Exception){
            Result.failure(ex)
        }
    }


}