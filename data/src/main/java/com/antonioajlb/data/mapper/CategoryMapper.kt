package com.antonioajlb.data.mapper

import com.antonioajlb.data.local.entity.CategoryEntity
import com.antonioajlb.domain.model.Category

fun Category.toEntity(): CategoryEntity =
    CategoryEntity(
        id = id,
        name = name
    )

fun CategoryEntity.toDomain(): Category =
        Category(
            id = id,
            name = name
        )