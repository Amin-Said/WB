package me.aminsaid.data.repository

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import me.aminsaid.cityinput.domain.model.City
import me.aminsaid.cityinput.domain.model.CityResponse
import me.aminsaid.cityinput.domain.model.mockCity
import me.aminsaid.cityinput.domain.repository.CityRepositoryBase
import me.aminsaid.core.utils.Resource
import me.aminsaid.data.local.AppDataStoreBase
import me.aminsaid.data.remote.api.CityApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*
import retrofit2.Response
import kotlin.test.assertTrue

@RunWith(JUnit4::class)
class CityRepositoryTest {

    private lateinit var cityApi: CityApi
    private lateinit var appDataStore: AppDataStoreBase
    private lateinit var cityRepository: CityRepositoryBase

    @Before
    fun setUp() {
        cityApi = mockk()
        appDataStore = mockk()
        cityRepository = CityRepository(cityApi, appDataStore)
    }

    @Test
    fun `getSavedCityFlow returns flow from appDataStore`() {
        // Mock the flow of saved city from appDataStore
        val savedCityFlow: Flow<City?> = mockk()
        coEvery { appDataStore.cityFlow } returns savedCityFlow

        // Call the repository method
        val result = cityRepository.getSavedCityFlow()

        // Verify that the repository method returns the same flow as from appDataStore
        assertEquals(savedCityFlow, result)
    }

    @Test
    fun `saveCityLocally saves city in appDataStore`() = runBlocking {
        // Mock the city to be saved
        val city = mockCity

        // Mock the appDataStore behavior
        coEvery { appDataStore.saveCity(city) } returns Unit

        // Call the repository method
        val result = cityRepository.saveCityLocally(city)

        // Verify that the appDataStore.saveCity was called with the correct city
        coEvery { appDataStore.saveCity(city) }
        assertEquals(Unit, result)
    }

    @Test
    fun `clearCityLocally clears city in appDataStore`() = runBlocking {
        // Mock the appDataStore behavior
        coEvery { appDataStore.clearCity() } returns Unit

        // Call the repository method
        val result = cityRepository.clearCityLocally()

        // Verify that the appDataStore.clearCity was called
        coEvery { appDataStore.clearCity() }
        assertTrue { result.data == true }
    }

    @Test
    fun `searchOnCity() returns success`() = runBlocking {
        // Mock the query and the response from cityApi
        val query = "Cairo"
        val cityList = listOf(mockCity)
        val cityResponse = CityResponse(cityList)
        val response = Response.success(cityResponse)

        // Mock the cityApi behavior
        coEvery { cityApi.searchOnCity(query) } returns response

        // Call the repository method
        val result = cityRepository.searchOnCity(query)

        // Verify that the cityApi.searchOnCity was called with the correct query
        coVerify { cityApi.searchOnCity(query) }

        // Verify the result
        assertTrue(result is Resource.Success)
        assertEquals(cityResponse, result.data)
    }

    @Test
    fun  `searchOnCity() returns empty response`(): Unit = runBlocking {
        // Mock the query and an empty response from cityApi
        val query = "NonExistentCity"
        val cityResponse = CityResponse(emptyList())
        val response = Response.success(cityResponse)

        // Mock the cityApi behavior
        coEvery { cityApi.searchOnCity(query) } returns response

        // Call the repository method
        val result = cityRepository.searchOnCity(query)

        // Verify that the cityApi.searchOnCity was called with the correct query
        coVerify { cityApi.searchOnCity(query) }

        // Verify the result
        assertTrue(result is Resource.Success)
        result.data?.let { assertTrue(it.isEmpty()) }
    }

    @Test
    fun `searchOnCity() returns failure`() = runBlocking {
        // Mock the query and an error response from cityApi
        val query = "InvalidQuery"

        // Mock the cityApi behavior
        coEvery { cityApi.searchOnCity(query) } throws Exception("API error")

        // Call the repository method
        val result = cityRepository.searchOnCity(query)

        // Verify that the cityApi.searchOnCity was called with the correct query
        coEvery { cityApi.searchOnCity(query) }

        // Verify the result
        assert(result is Resource.Error)
        assertEquals("An error occurred: API error", (result as Resource.Error).message)
    }
}
