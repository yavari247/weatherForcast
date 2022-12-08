package com.example.weather.network.local

import android.content.Context
import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.weather.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader

class savedLocations(private val context: Context):RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)

        CoroutineScope(Dispatchers.IO).launch {
            fillWithStartingNotes(context.applicationContext)
        }


    }
    private fun loadJSONArray(context: Context): JSONArray?{

        val inputStream = context.resources.openRawResource(R.raw.locations)

        BufferedReader(inputStream.reader()).use {
            return JSONArray(it.readText())
        }
    }
    private suspend fun fillWithStartingNotes(context: Context){

        val dao = LocationDatabase.getInstance(context).locationDao
        var li:List<Location>
        val ptyList: MutableList<Location> = mutableListOf()

        try {
            val notes = loadJSONArray(context)
            if (notes != null){
                for (i in 0 until notes.length()){
                    val item = notes.getJSONObject(i)
                    val city = item.getString("city")
                    val country = item.getString("country")

                    val locationEntity = Location(city, country )

                    dao?.insertLocation(locationEntity)
                }
            }
        }

        catch (e: JSONException) {
            //Timber.d("fillWithStartingNotes: $e")
            Log.i("fillWith","fillWithStartingNotes: $e")
        }
    }
}