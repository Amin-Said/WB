package me.aminsaid.weathercurrent.domain.repository

import me.aminsaid.core.utils.Resource
import me.aminsaid.weathercurrent.domain.model.CurrentWeatherResponse

interface CurrentWeatherRepositoryBase {
    suspend fun getCurrentWeather(query:String): Resource<CurrentWeatherResponse>
}