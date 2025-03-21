package com.example.countriesapp.model

import kotlinx.serialization.ExperimentalSerializationApi

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys

/**
 * Data class that defines an Country which includes a name, Region, Capital.
 */
@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonIgnoreUnknownKeys
data class Country(
    val name: String,
    val region: String,
    val code: String,
    val capital: String,

)