package nik.borisov.hstest.domain.usecases

import nik.borisov.hstest.domain.Result
import nik.borisov.hstest.domain.entities.ProductCategoryItem
import nik.borisov.hstest.domain.repositories.ProductsRepository
import javax.inject.Inject

class ProductCategoryProviderUseCase @Inject constructor(
    private val repository: ProductsRepository
) {

    suspend fun provideProductCategoryList(): Result<List<ProductCategoryItem>> {
        return repository.provideProductCategoryList()
    }
}