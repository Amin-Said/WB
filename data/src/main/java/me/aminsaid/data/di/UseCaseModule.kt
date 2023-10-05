package me.aminsaid.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.aminsaid.cityinput.domain.repository.CityRepositoryBase
import me.aminsaid.cityinput.domain.useCase.base.ClearLocalCityUseCase
import me.aminsaid.cityinput.domain.useCase.base.SaveCityUseCase
import me.aminsaid.cityinput.domain.useCase.base.SavedCityUseCase
import me.aminsaid.cityinput.domain.useCase.base.SearchCityUseCase
import me.aminsaid.cityinput.domain.useCase.impl.GetClearLocalCityUseCase
import me.aminsaid.cityinput.domain.useCase.impl.GetSaveCityUseCase
import me.aminsaid.cityinput.domain.useCase.impl.GetSavedCityUseCase
import me.aminsaid.cityinput.domain.useCase.impl.GetSearchCityUseCase
import me.aminsaid.weathercurrent.domain.repository.CurrentWeatherRepositoryBase
import me.aminsaid.weathercurrent.domain.useCase.base.CurrentWeatherUseCase
import me.aminsaid.weathercurrent.domain.useCase.impl.GetCurrentWeatherUseCase
import me.aminsaid.weatherforecast.domain.repository.ForecastWeatherRepositoryBase
import me.aminsaid.weatherforecast.domain.useCase.base.ForecastWeatherUseCase
import me.aminsaid.weatherforecast.domain.useCase.impl.GetForecastWeatherUseCase


@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideGetSavedCityNameUseCase(repository: CityRepositoryBase): SavedCityUseCase {
        return GetSavedCityUseCase(repository)
    }

    @Provides
    fun provideGetSaveCityNameUseCase(repository: CityRepositoryBase): SaveCityUseCase {
        return GetSaveCityUseCase(repository)
    }

    @Provides
    fun provideGetSearchCityUseCase(repository: CityRepositoryBase): SearchCityUseCase {
        return GetSearchCityUseCase(repository)
    }

    @Provides
    fun provideGetClearLocalCityUseCase(repository: CityRepositoryBase): ClearLocalCityUseCase {
        return GetClearLocalCityUseCase(repository)
    }


    @Provides
    fun provideGetCurrentWeatherUseCase(repository: CurrentWeatherRepositoryBase): CurrentWeatherUseCase {
        return GetCurrentWeatherUseCase(repository)
    }

    @Provides
    fun provideGetForecastWeatherUseCase(repository: ForecastWeatherRepositoryBase): ForecastWeatherUseCase {
        return GetForecastWeatherUseCase(repository)
    }

}