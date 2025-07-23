package com.antonioajlb.data.mapper

import com.antonioajlb.data.local.entity.ProductEntity
import com.antonioajlb.data.local.relation.ProductWithCategoryRelation
import com.antonioajlb.domain.model.Product

fun ProductEntity.toDomain(): Product {
    return Product(
        id = id,
        name = name,
        price = price,
        image = image,
        quantity = 1
    )
}

fun Product.toEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        name = name,
        price = price,
        image = image,
        categoryId = category?.id
    )
}

fun ProductWithCategoryRelation.toDomain(): Product {
    return Product(
        id = product.id,
        name = product.name,
        price = product.price,
        image = product.image,
        quantity = 1,
        category = category?.toDomain()
    )
}