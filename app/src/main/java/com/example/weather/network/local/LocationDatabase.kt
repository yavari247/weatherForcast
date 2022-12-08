package com.example.weather.network.local

import android.annotation.SuppressLint
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weather.ui.fragments.weatherFragment

@Database(entities = [Location::class],version = 1)
abstract class LocationDatabase : RoomDatabase() {

    abstract val locationDao : LocationDao

    companion object{
      @Volatile
      private var INSTANCE : LocationDatabase? = null
          @SuppressLint("SuspiciousIndentation")
          fun getInstance(context: Context): LocationDatabase {
              synchronized(this){
                  var instance = INSTANCE
                      if(instance==null){
                          instance = Room.databaseBuilder(
                                 context.applicationContext,
                                 LocationDatabase::class.java,
                                 "locations"
                          )
                              //.addCallback(savedLocations(context))
                              .build()
                      }
                  return instance
              }
          }

    }
}

