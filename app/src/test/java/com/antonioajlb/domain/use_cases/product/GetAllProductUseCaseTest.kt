package com.antonioajlb.domain.use_cases.product

import com.antonioajlb.domain.irepository.ProductRepository
import com.antonioajlb.domain.model.Product
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class GetAllProductUseCaseTest {

    private lateinit var productRepository: ProductRepository
    private lateinit var getProductsUseCase: GetAllProductUseCase
    private lateinit var insertProductUseCase: InsertProductUseCase

    @Before
    fun onBefore() {
        productRepository = mock()
        insertProductUseCase = mock()
        getProductsUseCase = GetAllProductUseCase(productRepository)
    }

    @Test
    fun `when repository returns products, use case should return them`() = runTest {
        val fakeProducts = listOf(
            Product(
                id = 1,
                name = "Camiseta",
                price = 10.0,
                quantity = 1,
                image = null,
            )
        )
        whenever(productRepository.getAllProducts()).thenReturn(Result.success(fakeProducts))

        val result = getProductsUseCase()

        assertTrue(result.isSuccess)
        assertEquals(fakeProducts, result.getOrNull())
    }

    @Test
    fun `when repository returns error, use case should return error`() = runTest {
        val error = Exception("Error")
        whenever(productRepository.getAllProducts()).thenReturn(Result.failure(error))

        val result = getProductsUseCase()

        assertTrue(result.isFailure)
        assertEquals(error, result.exceptionOrNull())
    }

    @Test
    fun `when insert a product, it should be added to the repository`() = runTest {

    }


}