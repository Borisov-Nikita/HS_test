package nik.borisov.hstest.domain

sealed class Result<out T> {

    data object Pending : Result<Nothing>()

    data class Error(
        val exception: Exception
    ) : Result<Nothing>()

    data class Success<T>(
        val value: T
    ) : Result<T>()
}
