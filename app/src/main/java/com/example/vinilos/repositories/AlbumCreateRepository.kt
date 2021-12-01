package com.example.vinilos.repositories

import android.app.Application
import android.util.Log
import com.example.vinilos.providers.NetworkServiceAdapter
import com.google.gson.Gson
import org.json.JSONObject

class AlbumCreateRepository (val application: Application){
    suspend fun saveData(values: MutableMap<String, String>): Int{
        var gson = Gson().toJson(values)
        val albumValues = JSONObject(gson)
        Log.i("repository", albumValues.toString())
        return NetworkServiceAdapter.getInstance(application).createAlbum(albumValues)
    }
}