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
class CurrentWeatherRepositoryTest {

    private lateinit var weatherApi: WeatherApi
    private lateinit var weatherRepository: CurrentWeatherRepositoryBase

    @Before
    fun setUp() {
        weatherApi = mockk()
        weatherRepository = CurrentWeatherRepository(weatherApi)
    }

    @Test
    fun `getCurrentWeather() returns success`() = runBlocking {
        // Mock the query and the response from weatherApi
        val query = "Cairo"
        val currentWeatherResponse = currentWeatherResponseMock
        val response = Response.success(currentWeatherResponse)

        // Mock the weatherApi behavior
        coEvery { weatherApi.getCurrentWeather(query) } returns response

        // Call the repository method
        val result = weatherRepository.getCurrentWeather(query)

        // Verify that the weatherApi.getCurrentWeather was called with the correct query
        coVerify { weatherApi.getCurrentWeather(query) }

        // Verify the result
        assertTrue(result is Resource.Success)
        assertEquals(currentWeatherResponse, result.data)
    }

    @Test
    fun `getCurrentWeather() returns failure`() = runBlocking {
        // Mock the query and an error response from weatherApi
        val query = "InvalidCity"
        val errorResponse = Response.error<CurrentWeatherResponse>(
            400,
            "".toResponseBody("application/json".toMediaTypeOrNull())
        )

        // Mock the weatherApi behavior
        coEvery { weatherApi.getCurrentWeather(query) } returns errorResponse

        // Call the repository method
        val result = weatherRepository.getCurrentWeather(query)

        // Verify that the weatherApi.getCurrentWeather was called with the correct query
        coVerify { weatherApi.getCurrentWeather(query) }

        // Verify the result
        assertTrue(result is Resource.Error)
    }

}