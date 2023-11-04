package nik.borisov.hstest.domain.usecases

import kotlinx.coroutines.flow.Flow
import nik.borisov.hstest.domain.Result
import nik.borisov.hstest.domain.entities.ProductItem
import nik.borisov.hstest.domain.repositories.ProductsRepository
import javax.inject.Inject

class ProductProviderUseCase @Inject constructor(
    private val repository: ProductsRepository
) {

    fun provideProductList(categoryId: Int): Flow<Result<List<ProductItem>>> {
        return repository.provideProductList(categoryId)
    }

    suspend fun updateProductList(categoryId: Int) {
        repository.updateProductList(categoryId)
    }
}