package com.example.vinilos.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilos.models.Album
import com.example.vinilos.models.Collector
import com.example.vinilos.models.Performer
import com.example.vinilos.providers.NetworkServiceAdapter

class ArtistRepository (val application: Application){
    suspend fun refreshData(): List<Performer>{
        return NetworkServiceAdapter.getInstance(application).getArtists()
    }
}