package nik.borisov.hstest.presentation.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import nik.borisov.hstest.R
import nik.borisov.hstest.domain.Result
import nik.borisov.hstest.domain.entities.ProductCategoryItem
import nik.borisov.hstest.domain.usecases.ProductCategoryProviderUseCase
import nik.borisov.hstest.presentation.entities.BannerUi
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val productCategoryProviderUseCase: ProductCategoryProviderUseCase
) : ViewModel() {

    private val _banners = MutableLiveData<List<BannerUi>>()
    val banners: LiveData<List<BannerUi>> = _banners

    private val _categories = MutableLiveData<List<ProductCategoryItem>>()
    val categories: LiveData<List<ProductCategoryItem>> = _categories

    init {
        _banners.value = prepareFakeBanners()
        getProductCategories()
    }

    private fun getProductCategories() {
        viewModelScope.launch {
            when (val result = productCategoryProviderUseCase.provideProductCategoryList()) {
                is Result.Pending -> {
                    //TODO handle result
                }

                is Result.Error -> {
                    //TODO handle result
                }

                is Result.Success -> {
                    val value = result.value
                    _categories.value = value
                }
            }
        }
    }

    private fun prepareFakeBanners(bannersCount: Int = 8): List<BannerUi> {
        val drawablesId = listOf(
            R.drawable.banner_sample_1,
            R.drawable.banner_sample_2,
            R.drawable.banner_sample_3,
            R.drawable.banner_sample_4
        )
        return mutableListOf<BannerUi>().apply {
            for (id in 1..bannersCount) {
                add(id - 1, BannerUi(id, drawablesId.random()))
            }
        }.toList()
    }
}