package com.smesonero.beercatalog.network_model

data class WsBeer(

    // nombre, tagline
    //nombre, descripcion, imagen, abv, ibu, food pairing,  SEGUNDO FRAG

    val id : Int,
    val name: String,
    val tagline : String,
    val description : String,
    val image_url : String,
    val abv : String,
    val ibu : String,
    val food_pairing : List<String>





)