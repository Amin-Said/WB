package me.aminsaid.cityinput

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import me.aminsaid.cityinput.domain.repository.CityRepositoryBase
import me.aminsaid.cityinput.domain.useCase.base.ClearLocalCityUseCase
import me.aminsaid.cityinput.domain.useCase.impl.GetClearLocalCityUseCase
import me.aminsaid.core.utils.Resource
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals


class GetClearLocalCityUseCaseTest {

    private lateinit var cityRepository: CityRepositoryBase

    @Before
    fun setUp() {
        cityRepository = mockk()
    }

    @Test
    fun `invoke() returns success`() = runBlocking {
        // Mock repository response for a successful operation
        val mockResponse = Resource.Success(true)
        coEvery { cityRepository.clearCityLocally() } returns mockResponse

        // Create the GetClearLocalCityUseCase
        val clearLocalCityUseCase: ClearLocalCityUseCase = GetClearLocalCityUseCase(cityRepository)

        // Call the use case
        val result = clearLocalCityUseCase()

        // Verify the result
        assertEquals(mockResponse, result)
    }

    @Test
    fun `invoke() returns error`() = runBlocking {
        // Mock repository response for an error
        val mockResponse = Resource.Error<Boolean>("something wrong", null)
        coEvery { cityRepository.clearCityLocally() } returns mockResponse

        // Create the GetClearLocalCityUseCase
        val clearLocalCityUseCase: ClearLocalCityUseCase = GetClearLocalCityUseCase(cityRepository)

        // Call the use case
        val result = clearLocalCityUseCase()

        // Verify the result
        assertEquals(mockResponse, result)
    }


}
