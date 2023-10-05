package me.aminsaid.wb.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import me.aminsaid.wb.presentation.cityInput.CitiesAdapter
import me.aminsaid.wb.presentation.weatherInfo.ForecastAdapter


@Module
@InstallIn(FragmentComponent::class)
object AdapterModule {

    @Provides
    @FragmentScoped
    fun provideCitiesAdapter(): CitiesAdapter {
        return CitiesAdapter()
    }

    @Provides
    @FragmentScoped
    fun provideForecastAdapter(): ForecastAdapter {
        return ForecastAdapter()
    }
}
