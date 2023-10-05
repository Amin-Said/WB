package me.aminsaid.cityinput.domain.useCase.impl

import me.aminsaid.cityinput.domain.repository.CityRepositoryBase
import me.aminsaid.core.utils.Resource
import me.aminsaid.cityinput.domain.useCase.base.ClearLocalCityUseCase
import javax.inject.Inject

class GetClearLocalCityUseCase@Inject constructor(private val cityRepository: CityRepositoryBase) :
    ClearLocalCityUseCase {
    override suspend operator fun invoke(): Resource<Boolean> {
        return cityRepository.clearCityLocally()
    }
}