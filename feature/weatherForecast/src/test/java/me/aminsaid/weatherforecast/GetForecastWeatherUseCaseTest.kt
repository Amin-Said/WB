package me.aminsaid.weatherforecast


import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import me.aminsaid.core.utils.Resource
import me.aminsaid.weatherforecast.domain.model.WeatherForecastResponse
import me.aminsaid.weatherforecast.domain.model.weatherForecastResponseMock
import me.aminsaid.weatherforecast.domain.repository.ForecastWeatherRepositoryBase
import me.aminsaid.weatherforecast.domain.useCase.base.ForecastWeatherUseCase
import me.aminsaid.weatherforecast.domain.useCase.impl.GetForecastWeatherUseCase
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class GetForecastWeatherUseCaseTest {

    private lateinit var weatherRepository: ForecastWeatherRepositoryBase

    @Before
    fun setUp() {
        weatherRepository = mockk()
    }

    @Test
    fun `invoke() returns success`() = runBlocking {
        // Mock repository response for a successful operation
        val query = "Cairo"
        val mockResponse = Resource.Success(weatherForecastResponseMock) // Use TestResource.Success
        coEvery { weatherRepository.getCurrentWeatherForecast(query) } returns mockResponse

        // Create the GetForecastWeatherUseCase
        val getForecastWeatherUseCase: ForecastWeatherUseCase = GetForecastWeatherUseCase(weatherRepository)

        // Call the use case
        val result = getForecastWeatherUseCase(query)

        // Verify the result
        assertEquals(mockResponse, result)
    }

    @Test
    fun `invoke() returns error`() = runBlocking {
        // Mock repository response for an error
        val query = "InvalidCity"
        val mockResponse = Resource.Error<WeatherForecastResponse>("City not found", null, 404)
        coEvery { weatherRepository.getCurrentWeatherForecast(query) } returns mockResponse

        // Create the GetForecastWeatherUseCase
        val getForecastWeatherUseCase: ForecastWeatherUseCase = GetForecastWeatherUseCase(weatherRepository)

        // Call the use case
        val result = getForecastWeatherUseCase(query)

        // Verify the result
        assertEquals(mockResponse, result)
    }
}
