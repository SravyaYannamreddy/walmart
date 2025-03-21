package com.example.countriesapp.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.countriesapp.R
import com.example.countriesapp.model.Country
import com.example.countriesapp.ui.theme.CountriesAppTheme


@Composable
fun AppScreen(
        countriesUiState: CountriesUiState,
        retryAction: () -> Unit,
        modifier: Modifier = Modifier,
        contentPadding: PaddingValues = PaddingValues(0.dp)
    ) { Log.d("MyTagApp", "Debug log:$countriesUiState")
        when (countriesUiState) {
            is CountriesUiState.Loading -> LoadingScreen(modifier.size(200.dp))
            is CountriesUiState.Success ->
                CountriesListScreen(
                    countries = countriesUiState.countries,
                    modifier = modifier
                        .padding(
                            start = dimensionResource(R.dimen.padding_medium),
                            top = dimensionResource(R.dimen.padding_medium),
                            end = dimensionResource(R.dimen.padding_medium)
                        ),
                    contentPadding = contentPadding
                )
            else -> ErrorScreen(retryAction, modifier)
        }
    }

    /**
     * The home screen displaying the loading message.
     */
    @Composable
    fun LoadingScreen(modifier: Modifier = Modifier) {
        Log.d("MyTagApp", "Inside LoadingScreen")
        Image(
            painter = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.loading),
            modifier = modifier
        )
    }

    /**
     * The home screen displaying error message with re-attempt button.
     */
    @Composable
    fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(stringResource(R.string.loading_failed))
            Button(onClick = retryAction) {
                Text(stringResource(R.string.retry))
            }
        }
    }

    @Composable
    fun CountryCard(country: Country, modifier: Modifier = Modifier) {
        Card(
            modifier = modifier,
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = country.name + ",  ",
                     style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start

                )
                    Text(
                        text = country.region,
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = country.code,
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Justify,

                    )

                }
                Text(
                    text = country.capital,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
                )
            }
        }
    }

    @Composable
    private fun CountriesListScreen(
        countries: List<Country>,
        modifier: Modifier = Modifier,
        contentPadding: PaddingValues = PaddingValues(0.dp)
    ) {
        Log.d("MyTagApp", "Inside CountriesListScreen")
       
        LazyColumn(
            modifier = modifier,
            contentPadding = contentPadding,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(
                items = countries,
                key = { country ->
                    country.name
                }
            ) { country ->
                CountryCard(country = country, modifier = Modifier.fillMaxSize())
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun LoadingScreenPreview() {
        CountriesAppTheme {
            LoadingScreen(
                Modifier
                    .fillMaxSize()
                    .size(200.dp)
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun ErrorScreenPreview() {
        CountriesAppTheme {
            ErrorScreen({}, Modifier.fillMaxSize())
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun CountriesListScreenPreview() {
        CountriesAppTheme {

        }
    }

