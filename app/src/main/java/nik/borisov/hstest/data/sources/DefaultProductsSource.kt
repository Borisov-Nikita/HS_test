package nik.borisov.hstest.data.sources

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import nik.borisov.hstest.data.mapper.ProductsMapper
import nik.borisov.hstest.data.sources.local.ProductsDao
import nik.borisov.hstest.data.sources.local.model.ProductsDbModel
import nik.borisov.hstest.data.sources.remote.ProductsApiService
import nik.borisov.hstest.data.sources.remote.safeAsResult
import nik.borisov.hstest.domain.Result
import nik.borisov.hstest.domain.entities.ProductCategoryItem
import nik.borisov.hstest.domain.entities.ProductItem
import javax.inject.Inject

class DefaultProductsSource @Inject constructor(
    private val productsApiService: ProductsApiService,
    private val productsDao: ProductsDao,
    private val productsMapper: ProductsMapper
) : ProductsSource {

    private val productListFlowMap =
        mutableMapOf<Int, MutableSharedFlow<Result<List<ProductItem>>>>()

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    override fun provideProductList(categoryId: Int): Flow<Result<List<ProductItem>>> {
        if (!productListFlowMap.containsKey(categoryId)) {
            productListFlowMap[categoryId] = MutableSharedFlow()
        }
        coroutineScope.launch {
            delay(100)
            downloadProductList(categoryId)
        }
        return productListFlowMap[categoryId]?.asSharedFlow()
            ?: throw NullPointerException("Doesn't have value in ProductFlowMap by key: $categoryId")
    }

    override suspend fun downloadProductList(categoryId: Int) {
        val flow = productListFlowMap[categoryId]
        flow?.emit(Result.Pending)
        val remoteResponseResult =
            productsApiService.downloadProducts(categoryId.toString())
                .safeAsResult { productDtoList ->
                    productsMapper.mapProductDtoListToEntityList(productDtoList)
                }
        when (remoteResponseResult) {
            is Result.Success -> {
                productsDao.addProducts(
                    ProductsDbModel(
                        id = categoryId,
                        categoryName = getCategoryNameById(categoryId),
                        productList = remoteResponseResult.value
                    )
                )
                flow?.emit(remoteResponseResult)
            }

            is Result.Error -> {
                val productsDbModel = productsDao.getProductsById(categoryId)
                if (productsDbModel == null || productsDbModel.productList.isEmpty()) {
                    flow?.emit(remoteResponseResult)
                } else {
                    flow?.emit(
                        Result.Success(
                            productsDbModel.productList
                        )
                    )
                }
            }

            is Result.Pending -> {
            }
        }
    }


    override suspend fun provideProductCategoryList(): Result<List<ProductCategoryItem>> {
        val productCategoriesFromDb = productsDao.getProductCategories()
        return if (productCategoriesFromDb.size < ProductCategories.values().size) {
            Result.Success(getFakeCategories())
        } else {
            Result.Success(
                productsMapper.mapProductCategoriesFromDbToEntities(productCategoriesFromDb)
            )
        }
    }

    private fun getFakeCategories(): List<ProductCategoryItem> {
        return ProductCategories.values().map {
            ProductCategoryItem(
                it.categoryId,
                it.categoryName
            )
        }
    }

    private fun getCategoryNameById(id: Int): String {
        return when (id) {
            ProductCategories.PIZZA.categoryId -> ProductCategories.PIZZA.categoryName
            ProductCategories.COMBO.categoryId -> ProductCategories.COMBO.categoryName
            ProductCategories.SNACKS.categoryId -> ProductCategories.SNACKS.categoryName
            ProductCategories.DESSERTS.categoryId -> ProductCategories.DESSERTS.categoryName
            ProductCategories.DRINKS.categoryId -> ProductCategories.DRINKS.categoryName
            else -> throw IllegalStateException("Unknown category id: $id")
        }
    }

    private enum class ProductCategories(
        val categoryId: Int,
        val categoryName: String
    ) {
        PIZZA(1, "Pizza"),
        COMBO(2, "Combo"),
        SNACKS(3, "Snacks"),
        DESSERTS(4, "Desserts"),
        DRINKS(5, "Drinks");
    }
}