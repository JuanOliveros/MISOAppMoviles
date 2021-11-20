package com.example.vinilos.models

data class CollectorAlbum (
    val id:Int,
    var price:Int?,
    var status:String?,
    var album:Album?,
    var collector:Collector?
) {}