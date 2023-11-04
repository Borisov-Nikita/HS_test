package nik.borisov.hstest.presentation.menu

sealed class ProductsFragmentState<out T> {

    data object Pending : ProductsFragmentState<Nothing>()

    data class Error(
        val exception: Exception
    ) : ProductsFragmentState<Nothing>()

    data class Success<T>(
        val value: T
    ) : ProductsFragmentState<T>()
}