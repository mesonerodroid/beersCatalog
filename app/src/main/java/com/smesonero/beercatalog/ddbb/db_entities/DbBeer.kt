package com.smesonero.beercatalog.ddbb.db_entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DbBeer (

    @PrimaryKey val id: Int,
    @ColumnInfo (name = "name")val name : String,
    @ColumnInfo (name = "tagline")val tagline : String,
    @ColumnInfo (name = "description")val description : String,
    @ColumnInfo (name = "image_url")val image_url : String,
    @ColumnInfo (name = "abv")val abv : String,
    @ColumnInfo (name = "ibu")val ibu : String,
    @ColumnInfo (name = "available")val available : Boolean
)