package com.example.weather.viewmodel

import android.content.Context.MODE_PRIVATE
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

    }
    fun save(){
        viewModelScope.launch {
            insert(Location("tehran","iran",0))
            insert(Location("newyork","usa",1))
            insert(Location("yazd","iran",2))
        }
    }
   // val locationsData = locationDatabase.locationDao.getAllLocations()


    fun getAllLocations(){
        viewModelScope.launch {
            locationDatabase.locationDao.getAllLocations().collect(){
                    items -> item.postValue(items)
            }
        }

    }



     fun insert(location: Location) = viewModelScope.launch {
        var newRowId: Long = locationDatabase.locationDao.insertLocation(location)
        if (newRowId > -1) {
            Log.i("successfully", "added")
        }
    }

//     fun clearAllLocations()=viewModelScope.launch {
//        locationRepo.deleteAll()
//    }


}