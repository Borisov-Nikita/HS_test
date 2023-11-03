package nik.borisov.hstest.presentation.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nik.borisov.hstest.presentation.entities.ProductUi

class ProductsViewModel : ViewModel() {

    private val _products = MutableLiveData<List<ProductUi>>()
    val banners: LiveData<List<ProductUi>> = _products

    init {
        _products.value = prepareFakeProducts()
    }

    private fun prepareFakeProducts(bannersCount: Int = 20): List<ProductUi> {
        return mutableListOf<ProductUi>().apply {
            for (id in 1..bannersCount) {
                add(id - 1, ProductUi(id))
            }
        }
    }
}