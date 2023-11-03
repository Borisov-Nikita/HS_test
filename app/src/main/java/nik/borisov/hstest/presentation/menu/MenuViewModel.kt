package nik.borisov.hstest.presentation.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nik.borisov.hstest.R
import nik.borisov.hstest.presentation.entities.BannerUi

class MenuViewModel : ViewModel() {

    private val _banners = MutableLiveData<List<BannerUi>>()
    val banners: LiveData<List<BannerUi>> = _banners

    init {
        _banners.value = prepareFakeBanners()
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