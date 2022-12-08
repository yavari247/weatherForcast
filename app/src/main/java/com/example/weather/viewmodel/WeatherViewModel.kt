package com.example.weather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.weather.repository.ForcastWeather
import com.example.weather.model.weather
import kotlinx.coroutines.launch

class WeatherViewModel(address: String, forcastWeather: ForcastWeather

) : ViewModel( ){
   // private lateinit var listener: SendSuccesfullText

    val weather=MutableLiveData<weather?>()
    init {

        viewModelScope.launch {
         //   insert(Location(0, "Tehran", "Iran"))
            val res: weather?=forcastWeather.showWeather(address)
            weather.postValue(res)
        }
    }
//    fun insert(location: Location) = viewModelScope.launch {
//        var newRowId: Long = locationRepo.insert(location)
//        if (newRowId > -1){
//            Log.i("successfully","added")
//        }
    }




