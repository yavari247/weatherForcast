package com.example.weather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weather.repository.ForcastWeather
import com.example.weather.repository.LocationRepo

class WeatherViewModelFactory(
    private val address:String,
    private val forcastWeather: ForcastWeather,
    private val locationRepo: LocationRepo
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            return WeatherViewModel(address, forcastWeather,locationRepo) as T

        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}