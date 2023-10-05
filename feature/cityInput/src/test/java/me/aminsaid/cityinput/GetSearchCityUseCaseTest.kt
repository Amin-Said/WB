package me.aminsaid.cityinput

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import me.aminsaid.cityinput.domain.model.CityResponse
import me.aminsaid.cityinput.domain.model.mockCity
import me.aminsaid.cityinput.domain.repository.CityRepositoryBase
import me.aminsaid.cityinput.domain.useCase.base.SearchCityUseCase
import me.aminsaid.cityinput.domain.useCase.impl.GetSearchCityUseCase
import me.aminsaid.core.utils.Resource
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class GetSearchCityUseCaseTest {

    private lateinit var cityRepository: CityRepositoryBase

    @Before
    fun setUp() {
        cityRepository = mockk()
    }

    @Test
    fun `invoke() returns success`() = runBlocking {
        // Mock repository response for a successful operation
        val mockResponse = Resource.Success(CityResponse(listOf(mockCity)))
        coEvery { cityRepository.searchOnCity(any()) } returns mockResponse

        // Create the GetSearchCityUseCase
        val searchCityUseCase: SearchCityUseCase = GetSearchCityUseCase(cityRepository)

        // Call the use case
        val result = searchCityUseCase("Cairo")

        // Verify the result
        assertEquals(mockResponse, result)
    }

    @Test
    fun `invoke() returns error`() = runBlocking {
        // Mock repository response for an error
        val errorMessage = "City not found"
        val mockResponse = Resource.Error<CityResponse>(errorMessage, null)
        coEvery { cityRepository.searchOnCity(any()) } returns mockResponse

        // Create the GetSearchCityUseCase
        val searchCityUseCase: SearchCityUseCase = GetSearchCityUseCase(cityRepository)

        // Call the use case
        val result = searchCityUseCase("NonExistentCity")

        // Verify the result
        assertEquals(mockResponse, result)
    }



}
