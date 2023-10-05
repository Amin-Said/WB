package me.aminsaid.weathercurrent

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import me.aminsaid.core.utils.Resource
import me.aminsaid.weathercurrent.domain.model.CurrentWeatherResponse
import me.aminsaid.weathercurrent.domain.model.currentWeatherResponseMock
import me.aminsaid.weathercurrent.domain.repository.CurrentWeatherRepositoryBase
import me.aminsaid.weathercurrent.domain.useCase.base.CurrentWeatherUseCase
import me.aminsaid.weathercurrent.domain.useCase.impl.GetCurrentWeatherUseCase
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class GetCurrentWeatherUseCaseTest {

    private lateinit var weatherRepository: CurrentWeatherRepositoryBase

    @Before
    fun setUp() {
        weatherRepository = mockk()
    }

    @Test
    fun `invoke() returns success`() = runBlocking {
        // Mock repository response for a successful operation
        val query = "Cairo"
        val mockResponse = Resource.Success(currentWeatherResponseMock) // Use TestResource.Success
        coEvery { weatherRepository.getCurrentWeather(query) } returns mockResponse

        // Create the GetCurrentWeatherUseCase
        val getCurrentWeatherUseCase: CurrentWeatherUseCase = GetCurrentWeatherUseCase(weatherRepository)

        // Call the use case
        val result = getCurrentWeatherUseCase(query)

        // Verify the result
        assertEquals(mockResponse, result)
    }

    @Test
    fun `invoke() returns error`() = runBlocking {
        // Mock repository response for an error
        val query = "InvalidCity"
        val mockResponse = Resource.Error<CurrentWeatherResponse>("City not found", null, 404)
        coEvery { weatherRepository.getCurrentWeather(query) } returns mockResponse

        // Create the GetCurrentWeatherUseCase
        val getCurrentWeatherUseCase: CurrentWeatherUseCase = GetCurrentWeatherUseCase(weatherRepository)

        // Call the use case
        val result = getCurrentWeatherUseCase(query)

        // Verify the result
        assertEquals(mockResponse, result)
    }
}
