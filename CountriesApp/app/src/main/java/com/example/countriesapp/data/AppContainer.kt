package com.example.countriesapp.data

import com.example.countriesapp.network.CountriesApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.Retrofit
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType



interface AppContainer {
    val countriesRepository: CountriesRepository
}


class DefaultAppContainer : AppContainer {
    private val BASE_URL = "https://gist.githubusercontent.com/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()


    private val retrofitService: CountriesApiService by lazy {
        retrofit.create(CountriesApiService::class.java)
    }


    override val countriesRepository: CountriesRepository by lazy {
        DefaultCountriesRepository(retrofitService)
    }
}
