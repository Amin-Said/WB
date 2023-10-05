package me.aminsaid.weatherforecast.domain.repository

import me.aminsaid.core.utils.Resource
import me.aminsaid.weatherforecast.domain.model.WeatherForecastResponse

interface ForecastWeatherRepositoryBase {
    suspend fun getCurrentWeatherForecast(query:String): Resource<WeatherForecastResponse>

}