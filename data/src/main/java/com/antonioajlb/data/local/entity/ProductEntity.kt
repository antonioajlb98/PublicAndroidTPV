package com.antonioajlb.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "product",
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [Index("categoryId")]
)
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)val id: Long,
    val name: String,
    val price: Double,
    val image: String? = null,
    val categoryId: Long? = null
)

