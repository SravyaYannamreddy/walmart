package com.example.countriesapp.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.countriesapp.CountriesApplication
import com.example.countriesapp.data.CountriesRepository
import com.example.countriesapp.model.Country
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

/**
 * UI state for the App screen
 */
sealed interface CountriesUiState {
    data class Success(val countries: List<Country>) : CountriesUiState
    object Error : CountriesUiState
    object Loading : CountriesUiState
}

class CountriesViewModel(private val countriesRepository: CountriesRepository) : ViewModel() {

    var countriesUiState: CountriesUiState by mutableStateOf(CountriesUiState.Loading)
        private set

    init {
        getCountries()
    }

    fun getCountries() {
        Log.d("MyTag", "Inside getCountries")
        viewModelScope.launch {
            Log.d("MyTag", "Inside launch")
            countriesUiState = CountriesUiState.Loading
            countriesUiState = try {
                Log.d("MyTag", "Inside try")
                CountriesUiState.Success(countriesRepository.getCountries())
            } catch (e: IOException) {
                Log.d("MyTag", "Inside IOException")
                CountriesUiState.Error
            } catch (e: HttpException) {
                Log.d("MyTag", "Inside HttpException")
                CountriesUiState.Error
            }
        }
    }

    /**
     * Factory for [CountriesViewModel] that takes [CountriesRepository] as a dependency
     */
   companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                        as CountriesApplication)
                val countriesRepository = application.container.countriesRepository
                CountriesViewModel(countriesRepository = countriesRepository)
            }
        }
    }
}
