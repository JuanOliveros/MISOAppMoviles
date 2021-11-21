package com.example.vinilos.repositories

import android.app.Application
import com.example.vinilos.models.Album
import com.example.vinilos.providers.NetworkServiceAdapter

class AlbumDetailRepository (val application: Application){
    suspend fun refreshData(id: Int): Album{
        return NetworkServiceAdapter.getInstance(application).getAlbum(id)
    }
}