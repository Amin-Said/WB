package me.aminsaid.weatherforecast.domain.useCase.impl

import me.aminsaid.core.utils.Resource
import me.aminsaid.weatherforecast.domain.useCase.base.ForecastWeatherUseCase
import me.aminsaid.weatherforecast.domain.model.WeatherForecastResponse
import me.aminsaid.weatherforecast.domain.repository.ForecastWeatherRepositoryBase
import javax.inject.Inject

class GetForecastWeatherUseCase  @Inject constructor(private val weatherRepository: ForecastWeatherRepositoryBase) :
    ForecastWeatherUseCase {
    override suspend fun invoke(query: String): Resource<WeatherForecastResponse> {
        return weatherRepository.getCurrentWeatherForecast(query)
    }
}