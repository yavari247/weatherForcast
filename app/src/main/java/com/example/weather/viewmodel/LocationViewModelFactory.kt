package com.example.weather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weather.network.local.LocationDatabase

class LocationViewModelFactory( private val locationDatabase: LocationDatabase): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LocationViewModel::class.java)) {
            return LocationViewModel(locationDatabase) as T

        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}