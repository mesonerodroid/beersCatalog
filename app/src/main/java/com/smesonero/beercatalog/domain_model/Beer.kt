package com.smesonero.beercatalog.domain_model

data class Beer(

    val id : Int,
    val name : String,
    val tagline : String,
    val description : String,
    val image_url : String,
    val abv : String,
    val ibu : String,
    val foodPairing : List<String>,
    val available : Boolean



)