package com.example.weather.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
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
    val countryItem=MutableLiveData<Event<List<Location>>>()
    val cityItem=MutableLiveData<List<Location>>()


    init {

    }
    fun save(){
        viewModelScope.launch {
            insert(Location("newyork","usa"))
            insert(Location("washington","usa"))
            insert(Location("tehran","iran"))
            insert(Location("yazd","iran"))
            insert(Location("berlin","germany"))
        }
    }



    fun getAllLocations(){
        viewModelScope.launch {
            locationDatabase.locationDao.getAllLocations().collect(){
                    items -> countryItem.postValue(Event(items))
            }
        }
    }

    fun getCities(country:String){
        viewModelScope.launch {
            locationDatabase.locationDao.getcities(country).collect(){
                    items -> cityItem.postValue(items)
            }
        }
    }


     fun insert(location: Location) = viewModelScope.launch {
        var newRowId: Long = locationDatabase.locationDao.insertLocation(location)
        if (newRowId > -1) {
            Log.i("successfully", "added")
        }
    }



}