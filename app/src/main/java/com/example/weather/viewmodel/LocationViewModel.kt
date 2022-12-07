package com.example.weather.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.network.local.Location
import com.example.weather.network.local.LocationDatabase
import com.example.weather.repository.LocationRepo
import kotlinx.coroutines.launch

class LocationViewModel(
    private val locationRepo: LocationRepo
) : ViewModel() {



    val locationsData = locationRepo.Locations


     fun insert(location: Location) = viewModelScope.launch {
        var newRowId: Long = locationRepo.insert(location)
        if (newRowId > -1) {
            Log.i("successfully", "added")
        }
    }
//     fun clearAllLocations()=viewModelScope.launch {
//        locationRepo.deleteAll()
//    }


}