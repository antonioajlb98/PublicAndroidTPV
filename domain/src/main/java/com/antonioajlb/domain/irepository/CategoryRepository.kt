package com.antonioajlb.domain.irepository

import com.antonioajlb.domain.model.Category

interface CategoryRepository {
    suspend fun getAllCategories(): Result<List<Category>>
    suspend fun insertCategory(category: Category): Result<Long>
    suspend fun updateCategory(category: Category): Result<Unit>
    suspend fun deleteCategory(category: Category): Result<Unit>
}