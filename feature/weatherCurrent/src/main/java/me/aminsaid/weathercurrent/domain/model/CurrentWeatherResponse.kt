package me.aminsaid.weathercurrent.domain.model

import me.aminsaid.core.utils.Measurement

typealias CurrentWeatherResponse = ArrayList<CurrentWeather>

data class CurrentWeather(
    val LocalObservationDateTime: String?,
    val EpochTime: Long?,
    val WeatherText: String?,
    val WeatherIcon: Int?,
    val HasPrecipitation: Boolean?,
    val PrecipitationType: String?,
    val IsDayTime: Boolean?,
    val Temperature: Temperature?,
    val RealFeelTemperature: Temperature?,

    )
data class Temperature(
    val Metric: Measurement?,
    val Imperial: Measurement?
)


val currentWeatherResponseMock: CurrentWeatherResponse = arrayListOf(
    CurrentWeather(
        LocalObservationDateTime = "2023-10-03T10:00:00+00:00",
        EpochTime = 1633258800,
        WeatherText = "Partly Cloudy",
        WeatherIcon = 3,
        HasPrecipitation = false,
        PrecipitationType = "",
        IsDayTime = true,
        Temperature = Temperature(
            Metric = Measurement(Value = 25.0, Unit = "°C", 0),
            Imperial = Measurement(Value = 77.0, Unit = "°F", 0)
        ),
        RealFeelTemperature = Temperature(
            Metric = Measurement(Value = 27.0, Unit = "°C", 0),
            Imperial = Measurement(Value = 81.0, Unit = "°F", 0)
        )
    )

)



