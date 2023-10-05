package me.aminsaid.weathercurrent.domain.useCase.impl

import me.aminsaid.core.utils.Resource
import me.aminsaid.weathercurrent.domain.model.CurrentWeatherResponse
import me.aminsaid.weathercurrent.domain.repository.CurrentWeatherRepositoryBase
import me.aminsaid.weathercurrent.domain.useCase.base.CurrentWeatherUseCase
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(private val weatherRepository: CurrentWeatherRepositoryBase) :
    CurrentWeatherUseCase {
    override suspend fun invoke(query: String): Resource<CurrentWeatherResponse> {
        return weatherRepository.getCurrentWeather(query)
    }
}