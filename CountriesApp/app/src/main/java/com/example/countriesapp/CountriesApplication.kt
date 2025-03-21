package com.example.countriesapp

import android.app.Application
import com.example.countriesapp.data.AppContainer
import com.example.countriesapp.data.DefaultAppContainer

class CountriesApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}