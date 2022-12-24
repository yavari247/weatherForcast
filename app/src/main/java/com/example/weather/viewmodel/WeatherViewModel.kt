package com.example.weather.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.Repository

import com.example.weather.repository.ForcastWeather
import com.example.weather.model.weather
import kotlinx.coroutines.launch

class WeatherViewModel @ViewModelInject constructor(
   repository: Repository,address: String, forcastWeather: ForcastWeather

) : ViewModel( ){
    val weather=MutableLiveData<weather?>()
//    init {
//
//        viewModelScope.launch {
//            val res: weather?=forcastWeather.showWeather(address)
//            weather.postValue(res)
//        }
//    }
    init {
        viewModelScope.launch {
            val res: weather?=repository.remote.getWeather(address)
            weather.postValue(res)
        }
    }
    }




