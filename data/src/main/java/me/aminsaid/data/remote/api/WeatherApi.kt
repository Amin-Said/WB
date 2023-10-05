package me.aminsaid.data.remote.api

import me.aminsaid.core.utils.Constants
import me.aminsaid.core.utils.Constants.CITY_ID_PATH
import me.aminsaid.core.utils.Constants.QUERY_DERAILS_ENABLED
import me.aminsaid.core.utils.Constants.QUERY_METRIC
import me.aminsaid.weatherforecast.domain.model.WeatherForecastResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {
    @GET(Constants.CURRENT_WEATHER_END_POINT)
    suspend fun getCurrentWeather(
        @Path(CITY_ID_PATH) query: String?,
        @Query(QUERY_DERAILS_ENABLED) isDetailsEnabled: Boolean = true
    ): Response<me.aminsaid.weathercurrent.domain.model.CurrentWeatherResponse>

    @GET(Constants.WEATHER_FOR_CAST_END_POINT)
    suspend fun getCurrentWeatherForecast(
        @Path(CITY_ID_PATH) query: String?,
        @Query(QUERY_DERAILS_ENABLED) isDetailsEnabled: Boolean = true,
        @Query(QUERY_METRIC) isMetricUnit: Boolean = true

    ): Response<WeatherForecastResponse>
}