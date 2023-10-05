package me.aminsaid.data.repository

import kotlinx.coroutines.flow.Flow
import me.aminsaid.cityinput.domain.model.City
import me.aminsaid.cityinput.domain.model.CityResponse
import me.aminsaid.cityinput.domain.repository.CityRepositoryBase
import me.aminsaid.core.utils.Resource
import me.aminsaid.data.local.AppDataStoreBase
import me.aminsaid.data.remote.api.CityApi
import me.aminsaid.data.utils.safeApiCall
import javax.inject.Inject

class CityRepository @Inject constructor(
    private val cityApi: CityApi,
    private val appDataStore: AppDataStoreBase
) : CityRepositoryBase {

    override fun getSavedCityFlow(): Flow<City?> {
        return appDataStore.cityFlow
    }

    override suspend fun saveCityLocally(city: City) {
        appDataStore.saveCity(city)
    }

    override suspend fun searchOnCity(query: String): Resource<CityResponse> {
        return safeApiCall { cityApi.searchOnCity(query) }
    }

    override suspend fun clearCityLocally(): Resource<Boolean> {
        return try {
            appDataStore.clearCity()
            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }


}
