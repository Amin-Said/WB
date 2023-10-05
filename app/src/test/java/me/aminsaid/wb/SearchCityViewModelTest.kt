package me.aminsaid.wb

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import me.aminsaid.cityinput.domain.model.CityResponse
import me.aminsaid.cityinput.domain.model.mockCity
import me.aminsaid.cityinput.domain.useCase.impl.GetSaveCityUseCase
import me.aminsaid.cityinput.domain.useCase.impl.GetSavedCityUseCase
import me.aminsaid.cityinput.domain.useCase.impl.GetSearchCityUseCase
import me.aminsaid.core.utils.Resource
import me.aminsaid.mockweathert.utils.CoroutineTestRule
import me.aminsaid.wb.presentation.cityInput.SearchCityViewModel
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@ExperimentalCoroutinesApi
class SearchCityViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = CoroutineTestRule()

    private lateinit var searchCityUseCase: GetSearchCityUseCase
    private lateinit var saveCityNameUseCase: GetSaveCityUseCase
    private lateinit var getSavedCityUseCase: GetSavedCityUseCase
    private lateinit var viewModel: SearchCityViewModel

    @Before
    fun setup() {
        searchCityUseCase = mockk()
        saveCityNameUseCase = mockk()
        getSavedCityUseCase = mockk()

        // Configure the mock response for GetSavedCityUseCase
        coEvery { getSavedCityUseCase.invoke() } returns flowOf(mockCity)

        viewModel = SearchCityViewModel(
            searchCityUseCase,
            saveCityNameUseCase,
            getSavedCityUseCase
        )
    }

    @Test
    fun `test search city loading`() = runTest {
        // Arrange
        val query = "Cairo"
        val loadingResource = Resource.Loading<CityResponse>(null)
        coEvery { searchCityUseCase(query) } returns loadingResource

        // Act
        viewModel.updateUserInput(query)

        // Assert
        val cityResultFlow = viewModel.cityResultFlow.asLiveData().value
      //  assertNotNull(cityResultFlow)
        assertEquals(loadingResource, cityResultFlow)
    }

    @Test
    fun `test search city success`() = runTest {
        // Arrange
        val query = "Cairo"
        val mockResponse = CityResponse(listOf(mockCity))
        val successResource = Resource.Success(mockResponse)
        coEvery { searchCityUseCase(query) } returns successResource

        // Act
        viewModel.updateUserInput(query)

        // Assert
        val cityResultFlow = viewModel.cityResultFlow.asLiveData().value
        assertNotNull(cityResultFlow)
        assertEquals(successResource, cityResultFlow)
    }

    @Test
    fun `test search city error`() = runTest {
        // Arrange
        val query = "InvalidCity"
        val errorMessage = "City not found"
        val errorResource = Resource.Error<CityResponse>(errorMessage, null, 404)
        coEvery { searchCityUseCase(query) } returns errorResource

        // Act
        viewModel.updateUserInput(query)

        // Assert
        val cityResultFlow = viewModel.cityResultFlow.asLiveData().value
        assertNotNull(cityResultFlow)
        assertEquals(errorResource, cityResultFlow)
    }

    @Test
    fun `test saving city name`() = runTest {
        // Arrange
        val city = mockCity
        coEvery { saveCityNameUseCase(city) } returns Unit

        // Act
        viewModel.saveCity(city)

        // Assert
        // Ensure that the saveCityNameUseCase is called with the provided city
        coVerify { saveCityNameUseCase(city) }
    }

    @Test
    fun `test get saved city`() = runTest {
        // Arrange
        val savedCity = mockCity
        coEvery { getSavedCityUseCase() } returns flowOf(savedCity)

        // Act
        val cityFlow = viewModel.cityFlow.asLiveData().value

        // Assert
        // Ensure that the viewModel retrieves the saved city correctly
        assertNotNull(cityFlow)
        assertEquals(savedCity, cityFlow)
    }


    @After
    fun tearDown() {
        // Reset the main dispatcher after the test
        Dispatchers.resetMain()
    }
}