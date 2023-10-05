package me.aminsaid.cityinput.domain.useCase.impl

import me.aminsaid.cityinput.domain.model.CityResponse
import me.aminsaid.cityinput.domain.repository.CityRepositoryBase
import me.aminsaid.cityinput.domain.useCase.base.SearchCityUseCase
import me.aminsaid.core.utils.Resource
import javax.inject.Inject

class GetSearchCityUseCase @Inject constructor(private val cityRepository: CityRepositoryBase) :
    SearchCityUseCase {
    override suspend fun invoke(query: String): Resource<CityResponse> {
        return cityRepository.searchOnCity(query)
    }
}