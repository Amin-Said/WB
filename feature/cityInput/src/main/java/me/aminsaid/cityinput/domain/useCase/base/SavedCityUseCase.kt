package me.aminsaid.cityinput.domain.useCase.base

import kotlinx.coroutines.flow.Flow
import me.aminsaid.cityinput.domain.model.City

interface SavedCityUseCase {
    operator fun invoke(): Flow<City?>
}