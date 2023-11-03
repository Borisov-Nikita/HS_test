package nik.borisov.hstest.presentation.entities

import androidx.annotation.DrawableRes

data class BannerUi(
    val id: Int,
    @DrawableRes val drawable: Int
)