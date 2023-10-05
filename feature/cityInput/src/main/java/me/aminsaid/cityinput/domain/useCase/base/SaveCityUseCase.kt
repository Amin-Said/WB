package me.aminsaid.cityinput.domain.useCase.base

import me.aminsaid.cityinput.domain.model.City

interface SaveCityUseCase {
    suspend operator fun invoke(city: City)
}