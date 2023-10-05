package me.aminsaid.weatherforecast.domain.useCase.base

import me.aminsaid.core.utils.Resource
import me.aminsaid.weatherforecast.domain.model.WeatherForecastResponse


interface ForecastWeatherUseCase {
    suspend operator fun invoke(query: String): Resource<WeatherForecastResponse>

}