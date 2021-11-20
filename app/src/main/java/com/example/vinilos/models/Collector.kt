package com.example.vinilos.models

data class Collector (
    val id:Int,
    var name:String?,
    var email:String?,
    var telephone:String?,
    var favoritePerformers:List<Performer>?,
    var comments:List<Comment>?,
    var collectorAlbums:List<CollectorAlbum>?
) {}