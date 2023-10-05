package me.aminsaid.wb.presentation.weatherInfo

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import me.aminsaid.cityinput.domain.model.City
import me.aminsaid.core.utils.Resource
import me.aminsaid.core.utils.getDayOfWeekWithTime
import me.aminsaid.core.utils.handleErrorState
import me.aminsaid.core.utils.hide
import me.aminsaid.core.utils.show
import me.aminsaid.weatherforecast.domain.model.ForecastWeatherViewState
import me.aminsaid.wb.databinding.FragmentWeatherInfoBinding
import me.aminsaid.weather.WeatherType
import me.aminsaid.weather.setWeatherImageState
import me.aminsaid.weathercurrent.domain.model.CurrentWeather
import javax.inject.Inject

@AndroidEntryPoint
class WeatherInfoFragment : Fragment() {

    private var _binding: FragmentWeatherInfoBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var mForecastAdapter: ForecastAdapter

    private val viewModel: WeatherViewModel by viewModels()

    private val args: WeatherInfoFragmentArgs by navArgs()

    private val callback: OnBackPressedCallback = object :
        OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            viewModel.clearCity()
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        callback.remove()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.updateSelectedCity(args.city)
        initRecyclerView()
        initListener()
        setObservers()



    }

    @SuppressLint("SetTextI18n")
    private fun setupWeatherData(response: CurrentWeather) {
        binding.dateTv.text = response.LocalObservationDateTime?.getDayOfWeekWithTime()
        binding.weatherMaxTempTv.text =
            "${response.Temperature?.Metric?.Value?.toInt()} ${response.Temperature?.Metric?.Unit}"
        binding.weatherStateTv.text = WeatherType.fromWMO(response.WeatherIcon ?: 0).weatherDesc
        binding.weatherStateIv.setWeatherImageState(response.WeatherIcon ?: 0)

    }

    @SuppressLint("SetTextI18n")
    private fun setupCityName(city: City) {
        binding.cityNameTv.text = "${city.LocalizedName}, ${city.Country?.LocalizedName}"

    }


    private fun initListener() {
        binding.editCity.setOnClickListener {
            viewModel.clearCity()
        }

    }

    private fun initRecyclerView() {
        binding.forCastRv.apply {
            adapter = mForecastAdapter
            layoutManager =
                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
        }
    }

    private fun openSearchCityScreen() {
        findNavController().popBackStack()
    }

    private fun hideProgress() {
        binding.progressBar.hide()
    }

    private fun showProgress() {
        binding.progressBar.show()
    }

    private fun showWeatherCard() {
        hideProgress()
        binding.currentWeatherCv.show()
    }

    private fun showRecyclerView() {
        hideProgress()
        binding.forCastRv.show()
    }


    private fun setObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.cityClearResultFlow.collect { result ->
                when (result) {
                    is Resource.Success -> {
                        openSearchCityScreen()
                    }
                    is Resource.Error -> {
                        requireActivity().handleErrorState(result.message, result.code)
                    }
                    else -> {}
                }
            }

        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.selectedCityFlow.collect {
                if (it.Key != null) {
                    setupCityName(it)
                }
            }

        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.currentWeatherResultFlow.collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        // Handle loading state
                        showProgress()
                    }

                    is Resource.Success -> {
                        // Handle success state
                        if (!result.data.isNullOrEmpty()) {
                            val response = result.data?.get(0)
                            response?.let { setupWeatherData(it) }
                            showWeatherCard()
                        }

                    }

                    is Resource.Error -> {
                        // Handle error state
                        requireActivity().handleErrorState(result.message, result.code)
                    }

                    else -> {}
                }
            }


        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.forecastWeatherState.collect { state ->
                when (state) {
                    is ForecastWeatherViewState.Loading -> {
                        // Handle loading state
                        showProgress()
                    }
                    is ForecastWeatherViewState.Success -> {
                        // Update your UI with the forecast data
                        val response = state.forecastResponse
                        response.let {
                            it.DailyForecasts?.let {
                                mForecastAdapter.submitList(it)
                                showRecyclerView()
                            }
                        }
                    }
                    is ForecastWeatherViewState.Error -> {
                        // Handle error state
                        requireActivity().handleErrorState( state.errorMessage, state.code)
                    }

                    else -> {}
                }
            }


        }
    }


}