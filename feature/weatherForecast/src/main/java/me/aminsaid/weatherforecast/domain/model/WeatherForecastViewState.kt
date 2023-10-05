package me.aminsaid.weatherforecast.domain.model


sealed class ForecastWeatherViewState {
    object Initial : ForecastWeatherViewState()
    object Loading : ForecastWeatherViewState()
    data class Success(val forecastResponse: WeatherForecastResponse) : ForecastWeatherViewState()
    data class Error(val errorMessage: String,val code:Int) : ForecastWeatherViewState()
}