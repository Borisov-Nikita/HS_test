package nik.borisov.hstest.data.sources.local.model

import androidx.room.ColumnInfo

data class CategoryTuple(

    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "category_name")
    val categoryName: String
)
