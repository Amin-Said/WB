package me.aminsaid.wb.presentation.cityInput

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import me.aminsaid.cityinput.domain.model.City
import me.aminsaid.cityinput.domain.model.CityResponse
import me.aminsaid.cityinput.domain.useCase.impl.GetSaveCityUseCase
import me.aminsaid.cityinput.domain.useCase.impl.GetSavedCityUseCase
import me.aminsaid.cityinput.domain.useCase.impl.GetSearchCityUseCase
import me.aminsaid.core.utils.Resource
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchCityViewModel @Inject constructor(
    private val searchCityUseCase: GetSearchCityUseCase,
    private val saveCityNameUseCase: GetSaveCityUseCase,
    private val getSavedCityUseCase: GetSavedCityUseCase
) : ViewModel() {

    private val _cityFlow = MutableStateFlow<City?>(null)
    val cityFlow: Flow<City?> = _cityFlow.asStateFlow()

    private val _cityResultFlow = MutableStateFlow<Resource<CityResponse>?>(null)
    val cityResultFlow: Flow<Resource<CityResponse>?> = _cityResultFlow.asStateFlow()

    private val userInputFlow = MutableStateFlow("")
    private var debounceDuration = 0L

    init {
        // Collect the Flow and update _cityNameFlow when it emits a value
        observeLocalSavedCity()

        // Observe user input and trigger search when there's a pause in typing
        observeUserInputAndSearch()

    }


    private fun observeLocalSavedCity(){
        viewModelScope.launch {
            getSavedCityUseCase().collect { city ->
                _cityFlow.value = city
            }
        }
    }

    private fun observeUserInputAndSearch() {
        userInputFlow
            .debounce(debounceDuration)
            .filter { it.isNotEmpty() }
            .distinctUntilChanged()
            .onEach { query ->
                viewModelScope.launch {
                    _cityResultFlow.value = Resource.Loading(null)
                    val result = searchCityUseCase(query)
                    _cityResultFlow.value = result
                }
            }
            .launchIn(viewModelScope)
    }

    // Function to update user input
    fun updateUserInput(query: String) {
        userInputFlow.value = query
    }

    fun saveCity(city: City) {
        viewModelScope.launch {
            saveCityNameUseCase(city)
        }
    }
}
