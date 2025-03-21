package com.example.countriesapp.data

import com.example.countriesapp.model.Country
import com.example.countriesapp.network.CountriesApiService


interface CountriesRepository {
    /** Retrieves list of countries from underlying data source */
    suspend fun getCountries(): List<Country>
}

/**
 * Network Implementation of repository that retrieves Country data from underlying data source.
 */
class DefaultCountriesRepository(
    private val countriesApiService: CountriesApiService
) : CountriesRepository {
    /** Retrieves list of countries from underlying data source */

    override suspend fun getCountries(): List<Country> = countriesApiService.getCountries()
}