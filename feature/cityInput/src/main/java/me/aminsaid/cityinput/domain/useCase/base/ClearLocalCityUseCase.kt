package me.aminsaid.cityinput.domain.useCase.base

import me.aminsaid.core.utils.Resource

interface ClearLocalCityUseCase {
    suspend operator fun invoke(): Resource<Boolean>
}