package nik.borisov.hstest.domain.repositories

import kotlinx.coroutines.flow.Flow
import nik.borisov.hstest.domain.Result
import nik.borisov.hstest.domain.entities.ProductCategoryItem
import nik.borisov.hstest.domain.entities.ProductItem

interface ProductsRepository {

    fun provideProductList(categoryId: Int): Flow<Result<List<ProductItem>>>

    suspend fun updateProductList(categoryId: Int)

    suspend fun provideProductCategoryList(): Result<List<ProductCategoryItem>>
}