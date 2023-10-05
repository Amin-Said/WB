package me.aminsaid.weather

import org.junit.Assert.assertEquals
import org.junit.Test

class WeatherTypeTest {

    @Test
    fun testFromWMO() {
        val codes = (1..45).toList()


        for (code in codes) {
            val expectedWeatherType = when (code) {
                1 -> WeatherType.Sunny
                2 -> WeatherType.MostlySunny
                3 -> WeatherType.PartlySunny
                4 -> WeatherType.PartlyCloudy
                5 -> WeatherType.HazySunshine
                6 -> WeatherType.MostlyCloudy
                7 -> WeatherType.Cloudy
                8 -> WeatherType.Overcast
                11 -> WeatherType.Foggy
                12 -> WeatherType.SlightRainShowers
                13 -> WeatherType.MostlyCloudyWithShowers
                14 -> WeatherType.PartlySunnyWithShowers
                15 -> WeatherType.SlightHailThunderstorm
                16 -> WeatherType.HeavyHailThunderstorm
                17 -> WeatherType.SlightHailThunderstorm
                18 -> WeatherType.Rain
                19 -> WeatherType.SnowGrains
                20 -> WeatherType.HeavySnowFall
                21 -> WeatherType.ModerateSnowFall
                22 -> WeatherType.SlightSnowFall
                23 -> WeatherType.MostlyCloudyWithSnow
                24 -> WeatherType.HeavyFreezingRain
                25 -> WeatherType.SlightSnowFall
                26 -> WeatherType.HeavyFreezingRain
                29 -> WeatherType.HeavyFreezingRain
                30 -> WeatherType.Hot
                31 -> WeatherType.Cold
                32 -> WeatherType.Windy
                33 -> WeatherType.ClearSky
                34 -> WeatherType.ClearSky
                35 -> WeatherType.PartlyCloudy
                36 -> WeatherType.PartlyCloudy
                37 -> WeatherType.PartlyCloudy
                38 -> WeatherType.MostlyCloudy
                39 -> WeatherType.PartlyCloudyWithShowers
                40 -> WeatherType.MostlyCloudyWithShowers
                41 -> WeatherType.PartlyCloudy
                42 -> WeatherType.MostlyCloudy
                43 -> WeatherType.MostlyCloudyWithSnow
                44 -> WeatherType.MostlyCloudyWithSnow
                45 -> WeatherType.ClearSky
                else -> WeatherType.ClearSky
            }

            val actualWeatherType = WeatherType.fromWMO(code)
            assertEquals("For code $code", expectedWeatherType, actualWeatherType)
        }
    }
}