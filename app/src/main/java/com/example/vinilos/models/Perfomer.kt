package com.example.vinilos.models

data class Perfomer (

    var id : Int?,
    var name : String?,
    var image : String?,
    var description : String?,
    var birthDate : String?,
    var creationDate: String?,
    var albums:List<Album>?,
    var performerPrizes:List<Prize>?,
    var musicians:List<String>?,

)