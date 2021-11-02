package com.example.vinilos.model

import java.util.*

data class Album (
    var id:Int,
    val name:String?,
    var cover:String?,
    var releaseDate:String?,
    var description:String?,
    var genre:String?,
    var recordLabel:String?,
    var tracks:List<Track>?,
    var performers:List<Perfomer>?,
    var comments:List<Comment>?
){
    public fun getArtists() : String? {
        return performers?.map { it.name }?.joinToString(",")
    }
}