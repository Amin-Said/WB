package me.aminsaid.data.repository

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import me.aminsaid.core.utils.Resource
import me.aminsaid.data.remote.api.WeatherApi
import me.aminsaid.data.repository.CurrentWeatherRepository
import me.aminsaid.weathercurrent.domain.model.CurrentWeatherResponse
import me.aminsaid.weathercurrent.domain.model.currentWeatherResponseMock
import me.aminsaid.weathercurrent.domain.repository.CurrentWeatherRepositoryBase
import me.aminsaid.weatherforecast.domain.model.WeatherForecastResponse
import me.aminsaid.weatherforecast.domain.model.weatherForecastResponseMock
import me.aminsaid.weatherforecast.domain.repository.ForecastWeatherRepositoryBase
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@RunWith(JUnit4::class)
class ForecastWeatherRepositoryTest {

    private lateinit var weatherApi: WeatherApi
    private lateinit var weatherRepository: ForecastWeatherRepositoryBase

    @Before
    fun setUp() {
        weatherApi = mockk()
        weatherRepository = ForecastWeatherRepository(weatherApi)
    }

    @Test
    fun `getCurrentWeatherForecast() returns success`() = runBlocking {
        // Mock the query and the response from weatherApi
        val query = "Cairo"
        val weatherForecastResponse = weatherForecastResponseMock
        val response = Response.success(weatherForecastResponse)

        // Mock the weatherApi behavior
        coEvery { weatherApi.getCurrentWeatherForecast(query) } returns response

        // Call the repository method
        val result = weatherRepository.getCurrentWeatherForecast(query)

        // Verify that the weatherApi.getCurrentWeatherForecast was called with the correct query
        coVerify { weatherApi.getCurrentWeatherForecast(query) }

        // Verify the result
        assertTrue(result is Resource.Success)
        assertEquals(weatherForecastResponse, result.data)
    }

    @Test
    fun `getCurrentWeatherForecast() returns failure`() = runBlocking {
        // Mock the query and an error response from weatherApi
        val query = "InvalidCity"
        val errorResponse = Response.error<WeatherForecastResponse>(
            400,
            "".toResponseBody("application/json".toMediaTypeOrNull())
        )

        // Mock the weatherApi behavior
        coEvery { weatherApi.getCurrentWeatherForecast(query) } returns errorResponse

        // Call the repository method
        val result = weatherRepository.getCurrentWeatherForecast(query)

        // Verify that the weatherApi.getCurrentWeatherForecast was called with the correct query
        coVerify { weatherApi.getCurrentWeatherForecast(query) }

        // Verify the result
        assertTrue(result is Resource.Error)
    }
}