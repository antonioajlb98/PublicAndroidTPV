package com.antonioajlb.di

import com.antonioajlb.domain.use_cases.product.DeleteProductUseCase
import com.antonioajlb.domain.use_cases.product.GetAllProductUseCase
import com.antonioajlb.domain.use_cases.product.InsertProductUseCase
import com.antonioajlb.domain.use_cases.product.UpdateProductUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetAllProductUseCase(get()) }
    factory { InsertProductUseCase(get()) }
    factory { DeleteProductUseCase(get()) }
    factory { UpdateProductUseCase(get()) }
}