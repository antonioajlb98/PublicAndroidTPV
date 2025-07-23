package com.antonioajlb.data.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.antonioajlb.data.local.entity.CategoryEntity
import com.antonioajlb.data.local.entity.ProductEntity

data class ProductWithCategoryRelation(
    @Embedded val product: ProductEntity,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "id"
    )
    val category: CategoryEntity?
)
