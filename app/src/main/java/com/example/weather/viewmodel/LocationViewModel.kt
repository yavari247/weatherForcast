package com.example.weather.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.network.local.Location
import com.example.weather.network.local.LocationDatabase
import kotlinx.coroutines.launch

class LocationViewModel(
    private val locationDatabase: LocationDatabase
) : ViewModel() {
    val countryItem=MutableLiveData<List<Location>>()
    val cityItem=MutableLiveData<List<Location>>()


    init {

    }
    fun save(){
        viewModelScope.launch {
            insert(Location("tehran","iran",0))
            insert(Location("tehran","iran",1))
            insert(Location("newyork","usa",2))
        }
    }
   // val locationsData = locationDatabase.locationDao.getAllLocations()


    fun getAllLocations(){
        viewModelScope.launch {
            locationDatabase.locationDao.getAllLocations().collect(){
                    items -> countryItem.postValue(items)
            }
        }
    }

    fun getCities(country:String){
        viewModelScope.launch {
            locationDatabase.locationDao.getcities(country).collect(){
                    items -> this@LocationViewModel.cityItem.postValue(items)
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