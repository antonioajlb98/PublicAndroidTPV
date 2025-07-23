package com.antonioajlb.di

import com.antonioajlb.ui.presentation.main.MainViewModel
import com.antonioajlb.ui.presentation.order.OrderViewModel
import com.antonioajlb.ui.presentation.product_management.ProductManagementViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewmodelModule = module {
    viewModel {
        MainViewModel()
    }
    viewModelOf(::OrderViewModel)
    viewModelOf(::ProductManagementViewModel)
}