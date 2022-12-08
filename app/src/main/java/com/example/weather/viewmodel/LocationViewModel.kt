package com.example.weather.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.network.local.Location
import com.example.weather.network.local.LocationDatabase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LocationViewModel(
    private val locationDatabase: LocationDatabase
) : ViewModel() {
    val item=MutableLiveData<List<Location>>()
    init {
        viewModelScope.launch {
            locationDatabase.locationDao.getAllLocations().collectLatest{
                items -> item.postValue(items)
            }
        }
    }

    val locationsData = locationDatabase.locationDao.getAllLocations()

//     fun insert(location: Location) = viewModelScope.launch {
//        var newRowId: Long = locationDatabase.locationDao.insertLocation(location)
//        if (newRowId > -1) {
//            Log.i("successfully", "added")
//        }
//    }

//     fun clearAllLocations()=viewModelScope.launch {
//        locationRepo.deleteAll()
//    }


}