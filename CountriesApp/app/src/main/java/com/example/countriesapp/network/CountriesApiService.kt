package com.example.countriesapp.network

import com.example.countriesapp.model.Country
import retrofit2.http.GET

interface CountriesApiService {
    @GET("countries.json")
    suspend fun getCountries(): List<Country>

}