package me.aminsaid.wb

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import me.aminsaid.cityinput.domain.useCase.impl.GetClearLocalCityUseCase
import me.aminsaid.core.utils.Resource
import me.aminsaid.mockweathert.utils.CoroutineTestRule
import me.aminsaid.wb.presentation.weatherInfo.WeatherViewModel
import me.aminsaid.weathercurrent.domain.model.CurrentWeatherResponse
import me.aminsaid.weathercurrent.domain.useCase.impl.GetCurrentWeatherUseCase
import me.aminsaid.weatherforecast.domain.model.ForecastWeatherViewState
import me.aminsaid.weatherforecast.domain.model.WeatherForecastResponse
import me.aminsaid.weatherforecast.domain.model.weatherForecastResponseMock
import me.aminsaid.weatherforecast.domain.useCase.impl.GetForecastWeatherUseCase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class WeatherViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = CoroutineTestRule()

    private lateinit var getCurrentWeatherUseCase: GetCurrentWeatherUseCase
    private lateinit var getForecastWeatherUseCase: GetForecastWeatherUseCase
    private lateinit var clearLocalCityUseCase: GetClearLocalCityUseCase
    private lateinit var viewModel: WeatherViewModel

    @Before
    fun setup() {
        getCurrentWeatherUseCase = mockk()
        getForecastWeatherUseCase = mockk()
        clearLocalCityUseCase = mockk()
        viewModel = WeatherViewModel(
            getCurrentWeatherUseCase,
            getForecastWeatherUseCase,
            clearLocalCityUseCase
        )

    }

    @Test
    fun `test loading current weather`() = runTest {
        // Arrange
        val query = "Cairo"
        val loadingResource = Resource.Loading<CurrentWeatherResponse>(null)
        coEvery { getCurrentWeatherUseCase(query) } returns loadingResource


        // Act
        viewModel.getWeatherData(query)

        // Assert
        val currentWeatherState = viewModel.currentWeatherResultFlow.asLiveData().value
        assertNotNull(currentWeatherState)
        assertEquals(loadingResource, currentWeatherState)
    }

    @Test
    fun `test success current weather`() = runTest {
        // Arrange
        val query = "Cairo"
        val mockResponse = CurrentWeatherResponse(/* your response data here */)
        val successResource = Resource.Success(mockResponse)
        coEvery { getCurrentWeatherUseCase(query) } returns successResource

        // Act
        viewModel.getWeatherData(query)

        // Assert
        val currentWeatherState = viewModel.currentWeatherResultFlow.asLiveData().value
        assertNotNull(currentWeatherState)
        assertEquals(successResource, currentWeatherState)
    }

    @Test
    fun `test error current weather`() = runTest {
        // Arrange
        val query = "InvalidCity"
        val errorMessage = "City not found"
        val errorResource = Resource.Error<CurrentWeatherResponse>(errorMessage, null, 404)
        coEvery { getCurrentWeatherUseCase(query) } returns errorResource

        // Act
        viewModel.getWeatherData(query)

        // Assert
        val currentWeatherState = viewModel.currentWeatherResultFlow.asLiveData().value
        assertNotNull(currentWeatherState)
        assertEquals(errorResource, currentWeatherState)
    }

    @Test
    fun `test loading forecast weather`() = runTest {
        // Arrange
        val query = "Cairo"
        val loadingResource = Resource.Loading<WeatherForecastResponse>(null)
        coEvery { getForecastWeatherUseCase(query) } returns loadingResource

        // Act
        viewModel.loadForecastWeather(query)

        // Assert
        val forecastWeatherState = viewModel.forecastWeatherState.value
        assertNotNull(forecastWeatherState)
        assertEquals(ForecastWeatherViewState.Loading, forecastWeatherState)
    }

    @Test
    fun `test success forecast weather`() = runTest {
        // Arrange
        val query = "Cairo"
        val successResource = Resource.Success(weatherForecastResponseMock)
        coEvery { getForecastWeatherUseCase(query) } returns successResource

        // Act
        viewModel.loadForecastWeather(query)

        // Assert
        val forecastWeatherState = viewModel.forecastWeatherState.value
        assertNotNull(forecastWeatherState)
        assertEquals(ForecastWeatherViewState.Success(weatherForecastResponseMock), forecastWeatherState)
    }

    @Test
    fun `test error forecast weather`() = runTest {
        // Arrange
        val query = "InvalidCity"
        val errorMessage = "City not found"
        val errorResource = Resource.Error<WeatherForecastResponse>(errorMessage, null, 404)
        coEvery { getForecastWeatherUseCase(query) } returns errorResource

        // Act
        viewModel.loadForecastWeather(query)

        // Assert
        val forecastWeatherState = viewModel.forecastWeatherState.value
        assertNotNull(forecastWeatherState)
        assertEquals(ForecastWeatherViewState.Error(errorMessage, 404), forecastWeatherState)
    }

    @Test
    fun testClearCity() = runTest {
        // Arrange
        val successResource = Resource.Success(true)
        coEvery { clearLocalCityUseCase() } returns successResource

        // Act
        viewModel.clearCity()

        // Assert
        val cityClearResult = viewModel.cityClearResultFlow.first()
        assertTrue(cityClearResult is Resource.Success)
        assertTrue(cityClearResult.data == true)
    }

    @After
    fun tearDown() {
        // Reset the main dispatcher after the test
        Dispatchers.resetMain()
    }
}
