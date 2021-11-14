package com.example.vinilos.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilos.models.Album
import com.example.vinilos.providers.NetworkServiceAdapter

class AlbumDetailRepository (val application: Application){
    fun refreshData(id: Int, callback: (Album)->Unit, onError: (VolleyError)->Unit) {
        NetworkServiceAdapter.getInstance(application).getAlbum(id, {
            callback(it)
        },
            onError
        )
    }
}