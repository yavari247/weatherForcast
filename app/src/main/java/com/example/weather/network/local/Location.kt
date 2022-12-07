package com.example.weather.network.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location_table")
data class Location (

    var city : String,


    var country : String,




    @PrimaryKey(autoGenerate = true)
    var id : Int=0



)