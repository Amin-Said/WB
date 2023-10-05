package me.aminsaid.data.remote

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import me.aminsaid.cityinput.domain.model.CityResponse
import me.aminsaid.cityinput.domain.model.cityResponseMock
import me.aminsaid.data.remote.api.CityApi
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import kotlin.test.assertEquals

class CityApiTest {

    private lateinit var cityApi: CityApi

    @Before
    fun setUp() {
        cityApi = mockk()
    }

    @Test
    fun `searchOnCity() returns success`() = runBlocking {
        // Mock API response
        val mockResponse = cityResponseMock
        val response = Response.success(mockResponse)
        coEvery { cityApi.searchOnCity(any()) } returns response

        // Call the function
        val result = cityApi.searchOnCity("New York")

        // Verify the result
        assertEquals(response.code(), result.code())
        assertEquals(mockResponse, result.body())
    }

    @Test
    fun `searchOnCity() returns failure`() = runBlocking {
        // Mock API response
        val response = Response.error<CityResponse>(
            400,
            "".toResponseBody("application/json".toMediaTypeOrNull())
        ) // Replace with your actual error response
        coEvery { cityApi.searchOnCity(any()) } returns response

        // Call the function
        val result = cityApi.searchOnCity("Cairo")

        // Verify the result
        assertEquals(response.code(), result.code())
    }
}