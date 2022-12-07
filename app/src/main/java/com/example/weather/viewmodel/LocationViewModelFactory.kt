package com.example.weather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weather.network.local.LocationDatabase
import com.example.weather.repository.LocationRepo

class LocationViewModelFactory(private val locationRepo: LocationRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LocationViewModel::class.java)) {
            return LocationViewModel(locationRepo) as T

        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}