package com.antonioajlb.ui.presentation.product_management

import com.antonioajlb.domain.use_cases.product.DeleteProductUseCase
import com.antonioajlb.domain.use_cases.product.GetAllProductUseCase
import com.antonioajlb.domain.use_cases.product.InsertProductUseCase
import com.antonioajlb.domain.use_cases.product.UpdateProductUseCase
import com.antonioajlb.ui.core.BaseViewModel

class ProductManagementViewModel(
    private val getAllProductUseCase: GetAllProductUseCase,
    private val insertProductUseCase: InsertProductUseCase,
    private val deleteProductUseCase: DeleteProductUseCase,
    private val updateProductUseCase: UpdateProductUseCase
) : BaseViewModel<ProductManagementScreenState>(ProductManagementScreenState()) {

    fun onEvent(event: ProductManagementScreenEvent) {
        when (event) {
            is ProductManagementScreenEvent.GetAllProducts -> {
                launchInViewModelScope {
                    getAllProductUseCase()
                        .onSuccess {
                            updateState {
                                copy(products = it)
                            }
                        }.onFailure {

                        }
                }
            }

            is ProductManagementScreenEvent.InsertProduct -> {
                launchInViewModelScope {
                    insertProductUseCase(event.product)
                        .onSuccess {
                            onEvent(ProductManagementScreenEvent.GetAllProducts)
                        }
                        .onFailure {

                        }
                }
            }

            is ProductManagementScreenEvent.RequestDeleteProduct -> {
                updateState {
                    copy(productToDelete = event.product)
                }
            }

            is ProductManagementScreenEvent.DismissDeleteProductConfirmation -> {
                updateState {
                    copy(productToDelete = null)
                }
            }

            is ProductManagementScreenEvent.DeleteProduct -> {
                launchInViewModelScope {
                    deleteProductUseCase(event.product)
                        .onSuccess {
                            updateState {
                                copy(
                                    products = products.filter { it.id != event.product.id },
                                    productToDelete = null
                                )
                            }
                        }
                        .onFailure {

                        }
                }
            }

            is ProductManagementScreenEvent.RequestUpdateProduct -> {
                updateState {
                    copy(productToUpdate = event.product)
                }
            }

            is ProductManagementScreenEvent.DismissUpdateProduct -> {
                updateState {
                    copy(productToUpdate = null)
                }
            }

            is ProductManagementScreenEvent.UpdateProduct -> {
                launchInViewModelScope {
                    updateProductUseCase(event.product)
                        .onSuccess {
                            updateState {
                                copy(
                                    products = products.map {
                                        if (it.id == event.product.id) {
                                            event.product
                                        } else {
                                            it
                                        }
                                    },
                                    productToUpdate = null
                                )
                            }
                        }
                        .onFailure {

                        }
                }
            }
        }
    }

}