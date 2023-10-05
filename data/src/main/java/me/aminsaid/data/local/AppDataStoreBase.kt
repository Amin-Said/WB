package me.aminsaid.data.local

import kotlinx.coroutines.flow.Flow
import me.aminsaid.cityinput.domain.model.City

interface AppDataStoreBase {
    val cityFlow: Flow<City?>
    suspend fun saveCity(city: City)
    suspend fun clearCity()

}
