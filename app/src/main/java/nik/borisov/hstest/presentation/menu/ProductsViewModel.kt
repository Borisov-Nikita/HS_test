package nik.borisov.hstest.presentation.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import nik.borisov.hstest.domain.Result
import nik.borisov.hstest.domain.usecases.ProductProviderUseCase

class ProductsViewModel @AssistedInject constructor(
    @Assisted private val categoryId: Int,
    private val productProviderUseCase: ProductProviderUseCase
) : ViewModel() {

    val screenState =
        productProviderUseCase.provideProductList(categoryId).map { result ->
            when (result) {
                is Result.Pending -> {
                    ProductsFragmentState.Pending
                }

                is Result.Error -> {
                    ProductsFragmentState.Error(result.exception)
                }

                is Result.Success -> {
                    ProductsFragmentState.Success(result.value)
                }
            }
        }.asLiveData()

    fun downloadProducts() {
        viewModelScope.launch {
            productProviderUseCase.updateProductList(categoryId)
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(categoryId: Int): ProductsViewModel
    }
}