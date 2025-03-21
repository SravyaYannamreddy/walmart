package com.example.countriesapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.countriesapp.R
import com.example.countriesapp.ui.screens.AppScreen
import com.example.countriesapp.ui.screens.CountriesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountriesApp() {
    Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = {
        TopAppBar(
            title = {
                Text(
                    stringResource(R.string.app_name),
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        )
    }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val countriesViewModel: CountriesViewModel =
                viewModel(factory = CountriesViewModel.Factory)
            AppScreen(
                countriesUiState = countriesViewModel.countriesUiState,
                retryAction = countriesViewModel::getCountries,
                modifier = Modifier.fillMaxSize(),
                contentPadding = it
            )
        }
    }
}