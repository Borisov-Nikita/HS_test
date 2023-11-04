package nik.borisov.hstest.data.sources.remote

import nik.borisov.hstest.domain.Result
import retrofit2.Response

inline fun <T, R> Response<T>.safeAsResult(
    mapper: (T) -> R
): Result<R> {
    return try {
        if (this.isSuccessful) {
            val body = this.body()
            body?.let {
                Result.Success(value = mapper(body))
            } ?: throw NullPointerException("Response body is empty")
        } else {
            throw Exception("Something went wrong")
        }
    } catch (e: Exception) {
        Result.Error(e)
    }
}