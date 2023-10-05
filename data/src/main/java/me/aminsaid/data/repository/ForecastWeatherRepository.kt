package me.aminsaid.data.repository

import me.aminsaid.core.utils.Resource
import me.aminsaid.data.remote.api.WeatherApi
import me.aminsaid.data.utils.safeApiCall
import me.aminsaid.weathercurrent.domain.model.CurrentWeatherResponse
import me.aminsaid.weathercurrent.domain.repository.CurrentWeatherRepositoryBase
import me.aminsaid.weatherforecast.domain.model.WeatherForecastResponse
import me.aminsaid.weatherforecast.domain.repository.ForecastWeatherRepositoryBase
import javax.inject.Inject

class ForecastWeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi
) : ForecastWeatherRepositoryBase {
    override suspend fun getCurrentWeatherForecast(query: String): Resource<WeatherForecastResponse> {
        return safeApiCall { weatherApi.getCurrentWeatherForecast(query) }

    }


}