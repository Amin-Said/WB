package me.aminsaid.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.aminsaid.cityinput.domain.model.City
import me.aminsaid.cityinput.domain.model.Country
import me.aminsaid.core.utils.Constants.CITY_KEY
import me.aminsaid.core.utils.Constants.CITY_NAME
import me.aminsaid.core.utils.Constants.COUNTRY_NAME
import javax.inject.Inject

class AppDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : AppDataStoreBase {

    override val cityFlow: Flow<City?> = dataStore.data.map { preferences ->
        City(
            LocalizedName = preferences[CityName],
            Key = preferences[CityKey],
            Country = Country(LocalizedName = preferences[countryName])
        )
    }

    override suspend fun saveCity(city: City) {
        dataStore.edit { preferences ->
            preferences[CityName] = city.LocalizedName ?: ""
            preferences[CityKey] = city.Key ?: ""
            preferences[countryName] = city.Country?.LocalizedName ?: ""
        }
    }

    override suspend fun clearCity() {
        dataStore.edit { preferences ->
            preferences.remove(CityName)
            preferences.remove(CityKey)
            preferences.remove(countryName)
        }
    }

    companion object {
        private val CityName = stringPreferencesKey(CITY_NAME)
        private val CityKey = stringPreferencesKey(CITY_KEY)
        private val countryName = stringPreferencesKey(COUNTRY_NAME)
    }
}


