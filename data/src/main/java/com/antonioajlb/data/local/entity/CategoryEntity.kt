package com.antonioajlb.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "category"
)
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)val id: Long,
    val name: String
)
