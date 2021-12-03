package com.example.vinilos.repositories

import android.app.Application
import com.example.vinilos.models.Collector
import com.example.vinilos.providers.NetworkServiceAdapter

class CollectorDetailRepository (val application: Application){
    suspend fun refreshData(id: Int): Collector{
        return NetworkServiceAdapter.getInstance(application).getCollector(id)
    }
}