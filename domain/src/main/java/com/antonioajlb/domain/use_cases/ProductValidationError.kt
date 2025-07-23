package com.antonioajlb.domain.use_cases

sealed class ProductValidationError {
    object EmptyName : ProductValidationError()
    object InvalidPrice : ProductValidationError()
}