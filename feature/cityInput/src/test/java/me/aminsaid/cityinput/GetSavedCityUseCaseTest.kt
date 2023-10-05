package me.aminsaid.cityinput

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.flow.toList
import me.aminsaid.cityinput.domain.model.City
import me.aminsaid.cityinput.domain.model.mockCity
import me.aminsaid.cityinput.domain.repository.CityRepositoryBase
import me.aminsaid.cityinput.domain.useCase.base.SavedCityUseCase
import me.aminsaid.cityinput.domain.useCase.impl.GetSavedCityUseCase

class GetSavedCityUseCaseTest {

    private lateinit var cityRepository: CityRepositoryBase

    @Before
    fun setUp() {
        cityRepository = mockk()
    }

    @Test
    fun `invoke() returns saved city`() = runBlocking {
        // Mock repository response for getting a saved city
        val savedCity = mockCity
        val mockFlow = flowOf(savedCity) // Create a flow with the saved city
        coEvery { cityRepository.getSavedCityFlow() } returns mockFlow

        // Create the GetSavedCityUseCase
        val getSavedCityUseCase: SavedCityUseCase = GetSavedCityUseCase(cityRepository)

        // Call the use case and collect the result from the flow
        val result = getSavedCityUseCase().toList()

        // Verify the result
        assertEquals(listOf(savedCity), result)
    }

    @Test
    fun `invoke() returns null when no city is saved`() = runBlocking {
        // Mock repository response for no saved city
        val mockFlow = flowOf<City?>(null) // Create an empty flow
        coEvery { cityRepository.getSavedCityFlow() } returns mockFlow

        // Create the GetSavedCityUseCase
        val getSavedCityUseCase: SavedCityUseCase = GetSavedCityUseCase(cityRepository)

        // Call the use case and collect the result from the flow
        val result = getSavedCityUseCase().toList()

        // Verify the result
        assertEquals(listOf(null), result)
    }
}