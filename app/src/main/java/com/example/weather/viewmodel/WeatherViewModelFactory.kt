package com.example.weather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weather.data.Repository
import com.example.weather.repository.ForcastWeather
import dagger.hilt.InstallIn


class WeatherViewModelFactory constructor(
    private val repository: Repository,
    private val address:String,
    private val forcastWeather: ForcastWeather

) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            return WeatherViewModel(repository,address, forcastWeather) as T

        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}