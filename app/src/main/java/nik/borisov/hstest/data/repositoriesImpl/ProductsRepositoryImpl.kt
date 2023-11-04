package nik.borisov.hstest.data.repositoriesImpl

import kotlinx.coroutines.flow.Flow
import nik.borisov.hstest.data.sources.ProductsSource
import nik.borisov.hstest.domain.Result
import nik.borisov.hstest.domain.entities.ProductCategoryItem
import nik.borisov.hstest.domain.entities.ProductItem
import nik.borisov.hstest.domain.repositories.ProductsRepository
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val productsSource: ProductsSource
) : ProductsRepository {

    override fun provideProductList(categoryId: Int): Flow<Result<List<ProductItem>>> {
        return productsSource.provideProductList(categoryId)
    }

    override suspend fun updateProductList(categoryId: Int) {
        productsSource.downloadProductList(categoryId)
    }

    override suspend fun provideProductCategoryList(): Result<List<ProductCategoryItem>> {
        return productsSource.provideProductCategoryList()
    }
}