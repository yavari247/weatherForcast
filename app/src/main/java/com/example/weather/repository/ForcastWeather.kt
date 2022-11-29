package com.example.weather.repository

import com.example.weather.model.weather

interface ForcastWeather {
  suspend  fun showWeather( location:String): weather?
}