package me.aminsaid.data.repository

import me.aminsaid.core.utils.Resource
import me.aminsaid.data.remote.api.WeatherApi
import me.aminsaid.data.utils.safeApiCall
import me.aminsaid.weathercurrent.domain.repository.CurrentWeatherRepositoryBase
import javax.inject.Inject

class CurrentWeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi
) : CurrentWeatherRepositoryBase {
    override suspend fun getCurrentWeather(query: String): Resource<me.aminsaid.weathercurrent.domain.model.CurrentWeatherResponse> {
        return safeApiCall { weatherApi.getCurrentWeather(query) }
    }
}