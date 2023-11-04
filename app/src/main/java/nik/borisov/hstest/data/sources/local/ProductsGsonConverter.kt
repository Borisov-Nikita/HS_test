package nik.borisov.hstest.data.sources.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import nik.borisov.hstest.domain.entities.ProductItem

class ProductsGsonConverter {

    @TypeConverter
    fun convertJsonToProductDtoList(json: String): List<ProductItem> {
        val type = object : TypeToken<List<ProductItem>>() {}.type
        return Gson().fromJson(json, type)
    }

    @TypeConverter
    fun convertProductDtoListToJson(list: List<ProductItem>): String {
        val type = object : TypeToken<List<ProductItem>>() {}.type
        return Gson().toJson(list, type)
    }
}