package com.antonioajlb.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.antonioajlb.data.local.entity.ProductEntity
import com.antonioajlb.data.local.relation.ProductWithCategoryRelation
import com.antonioajlb.domain.model.Product

@Dao
interface ProductDao {

    @Query("SELECT * FROM product")
    suspend fun getAllProducts(): List<ProductEntity>

    @Query("SELECT * FROM product")
    suspend fun getAllProductsWithCategory(): List<ProductWithCategoryRelation>

    @Insert
    suspend fun insertProduct(product: ProductEntity): Long

    @Update
    suspend fun updateProduct(product: ProductEntity)

    @Delete
    suspend fun deleteProduct(product: ProductEntity)

}