package com.example.vinilos.repositories

import android.app.Application
import com.example.vinilos.models.Track
import com.example.vinilos.providers.NetworkServiceAdapter
import com.google.gson.Gson
import org.json.JSONObject

class AlbumTracksRepository (val application: Application){
    suspend fun saveData(values: MutableMap<String, String>, albumId: Int): Int {
        var gson = Gson().toJson(values)
        val body = JSONObject(gson)
        return NetworkServiceAdapter.getInstance(application).addTrack(body, albumId)
    }
}