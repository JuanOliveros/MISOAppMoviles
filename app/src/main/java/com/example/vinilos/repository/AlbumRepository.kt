package com.example.vinilos.repository

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilos.model.Album
import com.example.vinilos.provider.NetworkServiceAdapter

class AlbumRepository (val application: Application){
    fun refreshData(callback: (List<Album>)->Unit, onError: (VolleyError)->Unit) {
        NetworkServiceAdapter.getInstance(application).getAlbums({
            callback(it)
        },
            onError
        )
    }
}