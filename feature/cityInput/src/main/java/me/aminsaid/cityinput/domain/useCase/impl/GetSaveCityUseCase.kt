package me.aminsaid.cityinput.domain.useCase.impl

import me.aminsaid.cityinput.domain.model.City
import me.aminsaid.cityinput.domain.repository.CityRepositoryBase
import me.aminsaid.cityinput.domain.useCase.base.SaveCityUseCase
import javax.inject.Inject

class GetSaveCityUseCase @Inject constructor(private val cityRepository: CityRepositoryBase) :
    SaveCityUseCase {
    override suspend fun invoke(city: City) {
        cityRepository.saveCityLocally(city)
    }
}