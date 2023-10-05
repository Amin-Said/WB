package me.aminsaid.weatherstate

import androidx.annotation.DrawableRes
import me.amin.weatherstate.R

sealed class WeatherType(
    val weatherDesc: String,
    @DrawableRes val iconRes: Int
) {
    object Sunny : WeatherType(
        weatherDesc = "Sunny",
        iconRes = R.drawable.ic_sunny
    )
    object PartlySunny : WeatherType(
        weatherDesc = "Partly Sunny",
        iconRes = R.drawable.ic_sunnycloudy
    )
    object MostlySunny : WeatherType(
        weatherDesc = "Mostly Sunny",
        iconRes = R.drawable.ic_sunnycloudy
    )
    object HazySunshine : WeatherType(
        weatherDesc = "Hazy Sunshine",
        iconRes = R.drawable.ic_sunny
    )
    object ClearSky : WeatherType(
        weatherDesc = "Clear sky",
        iconRes = R.drawable.ic_sunny
    )
    object PartlyCloudy : WeatherType(
        weatherDesc = "Partly cloudy",
        iconRes = R.drawable.ic_sunnycloudy
    )
    object Cold : WeatherType(
        weatherDesc = "Cold",
        iconRes = R.drawable.ic_very_cloudy
    )
    object Hot : WeatherType(
        weatherDesc = "Hot",
        iconRes = R.drawable.ic_sunny
    )
    object MostlyCloudy : WeatherType(
        weatherDesc = "Mostly cloudy",
        iconRes = R.drawable.ic_very_cloudy
    )
    object Cloudy : WeatherType(
        weatherDesc = "Cloudy",
        iconRes = R.drawable.ic_cloudy
    )
    object Overcast : WeatherType(
        weatherDesc = "Overcast",
        iconRes = R.drawable.ic_very_cloudy
    )
    object Foggy : WeatherType(
        weatherDesc = "Foggy",
        iconRes = R.drawable.ic_very_cloudy
    )
    object Windy : WeatherType(
        weatherDesc = "Storm",
        iconRes = R.drawable.ic_windy
    )
    object HeavyFreezingRain: WeatherType(
        weatherDesc = "Heavy freezing rain",
        iconRes = R.drawable.ic_snowyrainy
    )
    object SlightSnowFall: WeatherType(
        weatherDesc = "Slight snow fall",
        iconRes = R.drawable.ic_snowy
    )
    object ModerateSnowFall: WeatherType(
        weatherDesc = "Moderate snow fall",
        iconRes = R.drawable.ic_heavysnow
    )
    object HeavySnowFall: WeatherType(
        weatherDesc = "Heavy snow fall",
        iconRes = R.drawable.ic_heavysnow
    )
    object SnowGrains: WeatherType(
        weatherDesc = "Snow grains",
        iconRes = R.drawable.ic_heavysnow
    )
    object Rain: WeatherType(
        weatherDesc = "Rainy",
        iconRes = R.drawable.ic_rainshower
    )
    object MostlyCloudyWithShowers: WeatherType(
        weatherDesc = "Mostly Cloudy With Showers",
        iconRes = R.drawable.ic_rainshower
    )
    object PartlyCloudyWithShowers: WeatherType(
        weatherDesc = "Partly Cloudy With Showers",
        iconRes = R.drawable.ic_rainshower
    )
    object PartlySunnyWithShowers: WeatherType(
        weatherDesc = "Partly Sunny With Showers",
        iconRes = R.drawable.ic_sunnyrainy
    )
    object SlightRainShowers: WeatherType(
        weatherDesc = "Slight rain showers",
        iconRes = R.drawable.ic_rainshower
    )
    object MostlyCloudyWithSnow: WeatherType(
        weatherDesc = "Mostly Cloudy With Snow",
        iconRes = R.drawable.ic_snowy
    )
    object SlightHailThunderstorm: WeatherType(
        weatherDesc = "Thunderstorm with slight hail",
        iconRes = R.drawable.ic_rainythunder
    )
    object HeavyHailThunderstorm: WeatherType(
        weatherDesc = "Thunderstorm with heavy hail",
        iconRes = R.drawable.ic_rainythunder
    )



    companion object {
        fun fromWMO(code: Int): WeatherType {
            return when(code) {
                1 -> Sunny
                2 -> MostlySunny
                3 -> PartlySunny
                4 -> PartlyCloudy
                5 -> HazySunshine
                6 -> MostlyCloudy
                7 -> Cloudy
                8 -> Overcast
                11 -> Foggy
                12 -> SlightRainShowers
                13 -> MostlyCloudyWithShowers
                14 -> PartlySunnyWithShowers
                15 -> SlightHailThunderstorm
                16 -> HeavyHailThunderstorm
                17 -> SlightHailThunderstorm
                18 -> Rain
                19 -> SnowGrains
                20 -> HeavySnowFall
                21 -> ModerateSnowFall
                22 -> SlightSnowFall
                23 -> MostlyCloudyWithSnow
                24 -> HeavyFreezingRain
                25 -> SlightSnowFall
                26 -> HeavyFreezingRain
                29 -> HeavyFreezingRain
                30 -> Hot
                31 -> Cold
                32 -> Windy
                33 -> ClearSky
                34 -> ClearSky
                35 -> PartlyCloudy
                36 -> PartlyCloudy
                37 -> PartlyCloudy
                38 -> MostlyCloudy
                39 -> PartlyCloudyWithShowers
                40 -> MostlyCloudyWithShowers
                41 -> PartlyCloudy
                42 -> MostlyCloudy
                43 -> MostlyCloudyWithSnow
                44 -> MostlyCloudyWithSnow
                else -> ClearSky
            }
        }
    }
}
