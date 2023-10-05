package me.aminsaid.cityinput.domain.useCase.impl

import kotlinx.coroutines.flow.Flow
import me.aminsaid.cityinput.domain.model.City
import me.aminsaid.cityinput.domain.repository.CityRepositoryBase
import me.aminsaid.cityinput.domain.useCase.base.SavedCityUseCase
import javax.inject.Inject

class GetSavedCityUseCase @Inject constructor(private val cityRepository: CityRepositoryBase) :
    SavedCityUseCase {
    override operator fun invoke(): Flow<City?> {
        return cityRepository.getSavedCityFlow()
    }
}