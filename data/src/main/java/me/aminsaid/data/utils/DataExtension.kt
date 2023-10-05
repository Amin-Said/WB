package me.aminsaid.data.utils

import me.aminsaid.core.utils.ApiErrorHandler
import me.aminsaid.core.utils.Constants.API_ERROR
import me.aminsaid.core.utils.Constants.EMPTY_DATA_RESULT
import me.aminsaid.core.utils.Constants.SOMETHING_WENT_WRONG
import me.aminsaid.core.utils.Resource

suspend fun <T> safeApiCall(apiCall: suspend () -> retrofit2.Response<T>): Resource<T> {
    return try {
        val response = apiCall()

        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                Resource.Success(body)
            } else {
                Resource.Error(EMPTY_DATA_RESULT, code = ApiErrorHandler.EMPTY_RESPONSE_CODE)
            }
        } else {
            Resource.Error("$API_ERROR ${response.code()}", null, response.code())
        }
    } catch (e: Exception) {
        Resource.Error("$SOMETHING_WENT_WRONG ${e.message}", null)
    }
}