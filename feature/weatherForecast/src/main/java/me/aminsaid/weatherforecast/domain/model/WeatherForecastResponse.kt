package me.aminsaid.weatherforecast.domain.model

import me.aminsaid.core.utils.Measurement
import me.aminsaid.core.utils.TemperatureRange

data class WeatherForecastResponse(
    val Headline: WeatherHeadline?,
    val DailyForecasts: List<DailyForecast>?
)

data class WeatherHeadline(
    val EffectiveDate: String?,
    val EffectiveEpochDate: Long?,
    val Severity: Double?,
    val Text: String?,
    val Category: String?,
    val EndDate: String?,
    val EndEpochDate: Long?,
    val MobileLink: String?,
    val Link: String?
)

data class DailyForecast(
    val Date: String?,
    val EpochDate: Long?,
    val Sun: SunInfo?,
    val Moon: MoonInfo?,
    val Temperature: TemperatureRange?,
    val RealFeelTemperature: TemperatureRange?,
    val HoursOfSun: Double?=null,
    val DegreeDaySummary: DegreeDaySummary?,
    val Day: WeatherInfo?,
    val Night: WeatherInfo?,
)

data class SunInfo(
    val Rise: String?,
    val EpochRise: Long?,
    val Set: String?,
    val EpochSet: Long?
)

data class MoonInfo(
    val Rise: String?,
    val EpochRise: Long?,
    val Set: String?,
    val EpochSet: Long?,
    val Phase: String?,
    val Age: Int?
)

data class TemperatureInfo(
    val Value: Double?,
    val Unit: String?,
    val UnitType: Int?,
    val Phrase: String?
)

data class DegreeDaySummary(
    val Heating: TemperatureInfo?,
    val Cooling: TemperatureInfo?
)

data class WeatherInfo(
    val Icon: Int?,
    val IconPhrase: String?,
    val HasPrecipitation: Boolean?,
    val PrecipitationType: String?,
    val PrecipitationIntensity: String?,
)

val weatherForecastResponseMock = WeatherForecastResponse(
    Headline = WeatherHeadline(
        EffectiveDate = "2023-10-03",
        EffectiveEpochDate = 1633258800,
        Severity = 2.0,
        Text = "Weather forecast for the week",
        Category = "Week Forecast",
        EndDate = "2023-10-10",
        EndEpochDate = 1633863600,
        MobileLink = "https://example.com/forecast/mobile",
        Link = "https://example.com/forecast"
    ),
    DailyForecasts = listOf(
        DailyForecast(
            Date = "2023-10-03",
            EpochDate = 1633258800,
            Sun = SunInfo(
                Rise = "06:30",
                EpochRise = 1633270200,
                Set = "18:30",
                EpochSet = 1633314600
            ),
            Moon = MoonInfo(
                Rise = "14:45",
                EpochRise = 1633299900,
                Set = "02:30",
                EpochSet = 1633344600,
                Phase = "Full Moon",
                Age = 15
            ),
            Temperature = TemperatureRange(
                Minimum = Measurement(Value = 15.0, Unit = "°C"),
                Maximum = Measurement(Value = 25.0, Unit = "°C")
            ),
            RealFeelTemperature = TemperatureRange(
                Minimum = Measurement(Value = 16.0, Unit = "°C"),
                Maximum = Measurement(Value = 26.0, Unit = "°C")
            ), HoursOfSun = 5.5,
            DegreeDaySummary = DegreeDaySummary(
                Heating =
                TemperatureInfo(42.0, "°C", 0, ""),
                Cooling = TemperatureInfo(30.0, "°C", 0, "")
            ),
            Day = WeatherInfo(1, "Partly", true, "Sunny", "Partly"),
            Night = WeatherInfo(1, "Partly", true, "Cloudy", "Partly")
        ),
        DailyForecast(
            Date = "2023-10-04",
            EpochDate = 1633345200,
            Sun = SunInfo(
                Rise = "06:31",
                EpochRise = 1633356600,
                Set = "18:29",
                EpochSet = 1633401000
            ),
            Moon = MoonInfo(
                Rise = "15:45",
                EpochRise = 1633370700,
                Set = "03:30",
                EpochSet = 1633415400,
                Phase = "Waning Crescent",
                Age = 16
            ),
            Temperature = TemperatureRange(
                Minimum = Measurement(Value = 16.0, Unit = "°C", 0),
                Maximum = Measurement(Value = 26.5, Unit = "°C", 0)
            ),
            RealFeelTemperature = TemperatureRange(
                Minimum = Measurement(Value = 17.0, Unit = "°C", 0),
                Maximum = Measurement(Value = 27.0, Unit = "°C", 0)
            ), HoursOfSun = 5.5,
            DegreeDaySummary = DegreeDaySummary(
                Heating =
                TemperatureInfo(42.0, "°C", 0, ""),
                Cooling = TemperatureInfo(30.0, "°C", 0, "")
            ),
            Day = WeatherInfo(1, "Partly", true, "Sunny", "Partly"),
            Night = WeatherInfo(1, "Partly", true, "Cloudy", "Partly")
        )
    )
)



