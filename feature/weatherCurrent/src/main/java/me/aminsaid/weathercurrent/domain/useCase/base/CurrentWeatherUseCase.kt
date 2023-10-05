package me.aminsaid.weathercurrent.domain.useCase.base

import me.aminsaid.core.utils.Resource
import me.aminsaid.weathercurrent.domain.model.CurrentWeatherResponse


interface CurrentWeatherUseCase {
    suspend operator fun invoke(query: String): Resource<CurrentWeatherResponse>

}