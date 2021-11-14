package com.example.vinilos.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilos.models.Album
import com.example.vinilos.models.Perfomer
import com.example.vinilos.providers.NetworkServiceAdapter

class ArtistRepository (val application: Application){
    fun refreshData(callback: (List<Perfomer>)->Unit, onError: (VolleyError)->Unit) {
        NetworkServiceAdapter.getInstance(application).getArtists({
            callback(it)
        },
            onError
        )
    }
}