package com.example.weather.network.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(location: Location): Long

    @Query("SELECT * FROM location_table")
    fun getAllLocations(): LiveData<List<Location>>

    @Query("DELETE FROM location_table")
    suspend fun deleteAll()

}