package me.aminsaid.weatherforecast.domain.model



sealed class ForecastWeatherIntent {
    data class LoadForecastIntent(val query: String) : ForecastWeatherIntent()

}