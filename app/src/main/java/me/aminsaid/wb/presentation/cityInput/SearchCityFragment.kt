package me.aminsaid.wb.presentation.cityInput

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import me.aminsaid.cityinput.domain.model.City
import me.aminsaid.core.utils.Resource
import me.aminsaid.core.utils.getStringOnTextChanged
import me.aminsaid.core.utils.handleErrorState
import me.aminsaid.core.utils.hide
import me.aminsaid.core.utils.show
import me.aminsaid.wb.R
import me.aminsaid.wb.databinding.FragmentSearchCityBinding
import javax.inject.Inject

@AndroidEntryPoint
class SearchCityFragment : Fragment() {

    private var _binding: FragmentSearchCityBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var mCitiesAdapter: CitiesAdapter

    private val viewModel: SearchCityViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchCityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initListener()
        setObservers()
    }


    private fun initListener() {
        binding.cityNameEt.getStringOnTextChanged {
            viewModel.updateUserInput(it)
        }

    }

    private fun initRecyclerView() {
        binding.searchResultRv.apply {
            adapter = mCitiesAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        mCitiesAdapter.onCityClicked = { city ->
            city.Key?.let { viewModel.saveCity(city) }
            openWeatherInfoScreen(city)

        }
    }

    private fun openWeatherInfoScreen(city: City) {
        if (city.Key != null) {
            if (findNavController().currentDestination?.id == R.id.searchCityFragment) {
                val action =
                    SearchCityFragmentDirections.actionSearchCityFragmentToWeatherInfoFragment(city)
                findNavController().navigate(action)
            }
        } else {
            showSearchCard()
        }
    }

    private fun hideProgress() {
        binding.progressBar.hide()
    }

    private fun showProgress() {
        binding.progressBar.show()
    }

    private fun showSearchCard() {
        hideProgress()
        binding.cityNameCv.show()
    }

    private fun showRecyclerView() {
        hideProgress()
        binding.searchResultRv.show()
    }

    private fun setObservers() {
        viewLifecycleOwner.lifecycleScope.launch {

            viewModel.cityFlow.collect {
                it?.let {
                    openWeatherInfoScreen(it)
                }
                if (it == null) {
                    showSearchCard()
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {


            viewModel.cityResultFlow.collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        // Handle loading state
                        showProgress()
                    }

                    is Resource.Success -> {
                        // Handle success state
                        val cityResponse = result.data
                        if (!cityResponse.isNullOrEmpty()) {
                            cityResponse.let { mCitiesAdapter.submitList(it) }
                            showRecyclerView()
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
    }


}