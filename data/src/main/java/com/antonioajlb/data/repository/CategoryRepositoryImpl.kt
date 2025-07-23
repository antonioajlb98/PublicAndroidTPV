package com.antonioajlb.data.repository

import com.antonioajlb.data.local.dao.CategoryDao
import com.antonioajlb.data.mapper.toDomain
import com.antonioajlb.data.mapper.toEntity
import com.antonioajlb.domain.irepository.CategoryRepository
import com.antonioajlb.domain.model.Category

class CategoryRepositoryImpl(
    private val categoryDao: CategoryDao
): CategoryRepository {
    override suspend fun getAllCategories(): Result<List<Category>> {
        return try {
            Result.success(categoryDao.getAllCategories().map { it.toDomain() })
        } catch (ex: Exception){
            Result.failure(ex)
        }
    }

    override suspend fun insertCategory(category: Category): Result<Long> {
        return try {
            Result.success(categoryDao.insertCategory(category.toEntity()))
        }catch (ex: Exception){
            Result.failure(ex)
        }
    }

    override suspend fun updateCategory(category: Category): Result<Unit> {
        return try {
            Result.success(categoryDao.updateCategory(category.toEntity()))
        }catch (ex: Exception){
            Result.failure(ex)
        }
    }

    override suspend fun deleteCategory(category: Category): Result<Unit> {
        return try {
            Result.success(categoryDao.deleteCategory(category.toEntity()))
        }catch (ex: Exception){
            Result.failure(ex)
        }
    }
}