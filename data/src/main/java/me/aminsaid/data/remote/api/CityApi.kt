package me.aminsaid.data.remote.api

import me.aminsaid.cityinput.domain.model.CityResponse
import me.aminsaid.core.utils.Constants.QUERY_CITY_NAME
import me.aminsaid.core.utils.Constants.SEARCH_CITY_END_POINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CityApi{
    @GET(SEARCH_CITY_END_POINT)
    suspend fun searchOnCity(@Query(QUERY_CITY_NAME) query: String?): Response<CityResponse>


}