package me.aminsaid.cityinput.domain.repository

import kotlinx.coroutines.flow.Flow
import me.aminsaid.cityinput.domain.model.City
import me.aminsaid.cityinput.domain.model.CityResponse
import me.aminsaid.core.utils.Resource

interface CityRepositoryBase {
    suspend fun searchOnCity(query:String): Resource<CityResponse>
    fun getSavedCityFlow(): Flow<City?>
    suspend fun saveCityLocally(city: City)
    suspend fun clearCityLocally(): Resource<Boolean>

}