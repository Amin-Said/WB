package me.aminsaid.cityinput

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import me.aminsaid.cityinput.domain.model.mockCity
import me.aminsaid.cityinput.domain.repository.CityRepositoryBase
import me.aminsaid.cityinput.domain.useCase.base.SaveCityUseCase
import me.aminsaid.cityinput.domain.useCase.impl.GetSaveCityUseCase
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class GetSaveCityUseCaseTest {

    private lateinit var cityRepository: CityRepositoryBase

    @Before
    fun setUp() {
        cityRepository = mockk()
    }

    @Test
    fun `invoke() saves city locally`() = runBlocking {
        // Mock repository response for saving a city
        val cityToSave = mockCity
        coEvery { cityRepository.saveCityLocally(cityToSave) } returns Unit

        // Create the GetSaveCityUseCase
        val saveCityUseCase: SaveCityUseCase = GetSaveCityUseCase(cityRepository)

        // Call the use case
        val result = saveCityUseCase(cityToSave)

        // Verify the result
        assertEquals(Unit, result)
    }
}





