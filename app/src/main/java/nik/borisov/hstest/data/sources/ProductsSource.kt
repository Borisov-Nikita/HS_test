package nik.borisov.hstest.data.sources

import kotlinx.coroutines.flow.Flow
import nik.borisov.hstest.domain.Result
import nik.borisov.hstest.domain.entities.ProductCategoryItem
import nik.borisov.hstest.domain.entities.ProductItem

interface ProductsSource {

    fun provideProductList(categoryId: Int): Flow<Result<List<ProductItem>>>

    suspend fun downloadProductList(categoryId: Int)

    suspend fun provideProductCategoryList(): Result<List<ProductCategoryItem>>
}