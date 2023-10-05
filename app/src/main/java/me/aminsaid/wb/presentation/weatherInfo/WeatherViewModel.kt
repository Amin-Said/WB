package me.aminsaid.wb.presentation.weatherInfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import me.aminsaid.cityinput.domain.model.City
import me.aminsaid.cityinput.domain.useCase.impl.GetClearLocalCityUseCase
import me.aminsaid.core.utils.Resource
import me.aminsaid.weatherforecast.domain.model.ForecastWeatherIntent
import me.aminsaid.weatherforecast.domain.model.ForecastWeatherViewState
import me.aminsaid.weathercurrent.domain.model.CurrentWeatherResponse
import me.aminsaid.weathercurrent.domain.useCase.impl.GetCurrentWeatherUseCase
import me.aminsaid.weatherforecast.domain.useCase.impl.GetForecastWeatherUseCase
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val currentWeatherUseCase: GetCurrentWeatherUseCase,
    private val forecastWeatherUseCase: GetForecastWeatherUseCase,
    private val clearLocalCityUseCase: GetClearLocalCityUseCase
) : ViewModel() {

    val selectedCityFlow =
        MutableStateFlow(City(Key = null, LocalizedName = null, Country = null))

    private val _currentWeatherResultFlow =
        MutableStateFlow<Resource<CurrentWeatherResponse>?>(null)
    val currentWeatherResultFlow: Flow<Resource<CurrentWeatherResponse>?> =
        _currentWeatherResultFlow.asStateFlow()

    private val _forecastWeatherIntent: MutableSharedFlow<ForecastWeatherIntent> =
        MutableSharedFlow()
    val forecastWeatherIntent: SharedFlow<ForecastWeatherIntent> =
        _forecastWeatherIntent.asSharedFlow()

    // State Flow: Represents the current state of the UI for forecast weather
    private val _forecastWeatherState: MutableStateFlow<ForecastWeatherViewState> =
        MutableStateFlow(ForecastWeatherViewState.Initial)
    val forecastWeatherState: StateFlow<ForecastWeatherViewState> =
        _forecastWeatherState.asStateFlow()


    private val _cityClearResultFlow = MutableStateFlow<Resource<Boolean>?>(null)
    val cityClearResultFlow: Flow<Resource<Boolean>?> = _cityClearResultFlow.asStateFlow()

    init {
        // Observe selected city and get the weather
        observeSelectedCityAndGetWeather()

        // Handle forecast weather intents
        handleForecastWeatherIntent()
    }

    // Handle forecast weather data using MVI
    private fun handleForecastWeatherIntent() {
        viewModelScope.launch {
            forecastWeatherIntent.collect { intent ->
                when (intent) {
                    is ForecastWeatherIntent.LoadForecastIntent -> {
                        // Handle loading state
                        _forecastWeatherState.value = ForecastWeatherViewState.Loading

                        // Load forecast weather data using your forecastWeatherUseCase
                        val query = intent.query

                        // Handle the result
                        when (val forecastWeatherResult = forecastWeatherUseCase(query)) {
                            is Resource.Success -> {
                                // Handle success state
                                _forecastWeatherState.value = ForecastWeatherViewState.Success(
                                    forecastWeatherResult.data!!
                                )
                            }

                            is Resource.Error -> {
                                // Handle error state
                                _forecastWeatherState.value = ForecastWeatherViewState.Error(
                                    forecastWeatherResult.message ?: "",
                                    forecastWeatherResult.code ?: 0
                                )
                            }

                            else -> {
                                // Handle other states if necessary
                            }
                        }
                    }
                }
            }
        }
    }

    // Function to trigger loading forecast weather data
    fun loadForecastWeather(query: String) {
        viewModelScope.launch {
            _forecastWeatherIntent.emit(ForecastWeatherIntent.LoadForecastIntent(query))
        }
    }

    private fun observeSelectedCityAndGetWeather() {
        selectedCityFlow
            .filter { !it.Key.isNullOrEmpty() }
            .onEach { city ->
                city.Key?.let {
                    getWeatherData(it)
                    loadForecastWeather(it)
                }
            }
            .launchIn(viewModelScope)
    }

    fun updateSelectedCity(city: City) {
        selectedCityFlow.value = city
    }

    fun getWeatherData(query: String) {
        viewModelScope.launch {
            _currentWeatherResultFlow.value = Resource.Loading(null)
            _currentWeatherResultFlow.value = currentWeatherUseCase(query)
        }
    }

    fun clearCity() {
        viewModelScope.launch {
            val result = clearLocalCityUseCase()
            _cityClearResultFlow.value = result
        }
    }


}