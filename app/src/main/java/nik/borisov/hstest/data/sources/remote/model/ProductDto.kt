package nik.borisov.hstest.data.sources.remote.model

import com.google.gson.annotations.SerializedName

data class ProductDto(

    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("image_url")
    val imageUrl: String?
)
