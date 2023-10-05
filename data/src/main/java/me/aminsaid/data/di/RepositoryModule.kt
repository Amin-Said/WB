package me.aminsaid.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.aminsaid.cityinput.domain.repository.CityRepositoryBase
import me.aminsaid.data.local.AppDataStoreBase
import me.aminsaid.data.remote.api.CityApi
import me.aminsaid.data.remote.api.WeatherApi
import me.aminsaid.data.repository.CityRepository
import me.aminsaid.data.repository.CurrentWeatherRepository
import me.aminsaid.data.repository.ForecastWeatherRepository
import me.aminsaid.weathercurrent.domain.repository.CurrentWeatherRepositoryBase
import me.aminsaid.weatherforecast.domain.repository.ForecastWeatherRepositoryBase


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideCityRepository(api: CityApi, appDataStore: AppDataStoreBase): CityRepositoryBase {
        return CityRepository(api,appDataStore)
    }

    @Provides
    fun provideCurrentWeatherRepository(api: WeatherApi): CurrentWeatherRepositoryBase {
        return CurrentWeatherRepository(api)
    }

    @Provides
    fun provideForecastWeatherRepository(api: WeatherApi): ForecastWeatherRepositoryBase {
        return ForecastWeatherRepository(api)
    }
}