package com.antonioajlb.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.antonioajlb.data.local.dao.CategoryDao
import com.antonioajlb.data.local.dao.ProductDao
import com.antonioajlb.data.local.entity.CategoryEntity
import com.antonioajlb.data.local.entity.ProductEntity


@Database(
    entities = [
        ProductEntity::class,
        CategoryEntity::class
               ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun categoryDao(): CategoryDao
}