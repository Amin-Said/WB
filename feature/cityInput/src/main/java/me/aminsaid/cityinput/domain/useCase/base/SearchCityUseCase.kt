package me.aminsaid.cityinput.domain.useCase.base

import me.aminsaid.cityinput.domain.model.CityResponse
import me.aminsaid.core.utils.Resource

interface SearchCityUseCase {
    suspend operator fun invoke(query: String): Resource<CityResponse>
}