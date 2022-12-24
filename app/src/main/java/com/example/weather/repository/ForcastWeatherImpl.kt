package com.example.weather.repository

import android.util.Log
import com.example.weather.data.network.WeatherServiceApi
import com.example.weather.network.remoat.RetrofitInstance
import com.example.weather.model.weather
import java.lang.Exception

class ForcastWeatherImpl(
) : ForcastWeather {
    override suspend fun showWeather(location: String): weather? {
        val retService= RetrofitInstance.getRetrofitInstance().create(WeatherServiceApi::class.java)

        var dayWeather: weather?=null
        try {
            val response = retService.getWeather(location)

            if (response != null) {

               dayWeather=response

            }
        } catch (exception: Exception) {
            Log.i("MyTag", exception.message.toString())
        }
        return dayWeather
    }
}