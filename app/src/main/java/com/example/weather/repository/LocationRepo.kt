package com.example.weather.repository

import com.example.weather.network.local.Location
import com.example.weather.network.local.LocationDatabase

class LocationRepo(private val locationDatabase: LocationDatabase) {
    val Locations=locationDatabase.locationDao.getAllLocations()
    suspend fun insert(locationEntity: Location):Long{
        return  locationDatabase.locationDao.insertLocation(locationEntity)
    }
    suspend fun deleteAll(){
        locationDatabase.locationDao.deleteAll()
    }
}