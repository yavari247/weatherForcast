package com.example.weather.network

import com.example.weather.model.weather
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {

    companion object {
        const val unitGroup = "us"
        const val contentType = "json"
        const val key = "82RT7W76L2K688AC6BY37WRQA"
    }

    @GET("timeline/{address}?unitGroup=$unitGroup&key=$key&contentType=$contentType")
    suspend fun getWeather(
        @Path(value = "address"
        ) address: String,
    ): weather


}

