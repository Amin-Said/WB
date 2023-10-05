package me.aminsaid.data.remote

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import me.aminsaid.data.remote.api.WeatherApi
import me.aminsaid.weathercurrent.domain.model.CurrentWeatherResponse
import me.aminsaid.weathercurrent.domain.model.currentWeatherResponseMock
import me.aminsaid.weatherforecast.domain.model.WeatherForecastResponse
import me.aminsaid.weatherforecast.domain.model.weatherForecastResponseMock
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class WeatherApiTest {

    private lateinit var weatherApi: WeatherApi

    @Before
    fun setUp() {
        weatherApi = mockk()
    }

    @Test
    fun `getCurrentWeather() returns success`() = runBlocking {
        // Mock API response
        val mockResponse = currentWeatherResponseMock
        val response = Response.success(mockResponse)
        coEvery { weatherApi.getCurrentWeather(any(), any()) } returns response

        // Call the function
        val result = weatherApi.getCurrentWeather("12345", true)

        // Verify the result
        assertEquals(response.code(), result.code())
        assertEquals(mockResponse, result.body())
    }

    @Test
    fun `getCurrentWeather() returns failure`() = runBlocking {
        // Mock API response
        val response = Response.error<CurrentWeatherResponse>(
            400,
            "".toResponseBody("application/json".toMediaTypeOrNull())
        ) // Replace with actual error response
        coEvery { weatherApi.getCurrentWeather(any(), any()) } returns response

        // Call the function
        val result = weatherApi.getCurrentWeather("cityId", true)

        // Verify the result
        assertEquals(response.code(), result.code())
    }

    @Test
    fun `getCurrentWeatherForecast() returns success`() = runBlocking {
        // Mock API response
        val mockResponse = weatherForecastResponseMock
        val response = Response.success(mockResponse)
        coEvery { weatherApi.getCurrentWeatherForecast("12345", true, true) } returns response

        // Call the function
        val result = weatherApi.getCurrentWeatherForecast("12345", true, true)

        // Verify the result
        assertEquals(response.code(), result.code())
        assertEquals(mockResponse, result.body())
    }

    @Test
    fun `getCurrentWeatherForecast() returns failure`() = runBlocking {
        // Mock API response
        val response = Response.error<WeatherForecastResponse>(
            400,
            "".toResponseBody("application/json".toMediaTypeOrNull())
        ) // Replace with actual error response
        coEvery { weatherApi.getCurrentWeatherForecast(any(), any(), any()) } returns response

        // Call the function
        val result = weatherApi.getCurrentWeatherForecast("cityId", true, true)

        // Verify the result
        assertEquals(response.code(), result.code())
    }
}
