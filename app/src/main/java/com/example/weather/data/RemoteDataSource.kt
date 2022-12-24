package com.example.weather.data

import com.example.weather.data.network.WeatherServiceApi
import com.example.weather.model.weather
import retrofit2.http.Path
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val weatherServiceApi: WeatherServiceApi) {
    suspend fun getWeather(
        @Path(value = "address"
        ) address: String,
    ): weather{
        return weatherServiceApi.getWeather(address)
    }

}