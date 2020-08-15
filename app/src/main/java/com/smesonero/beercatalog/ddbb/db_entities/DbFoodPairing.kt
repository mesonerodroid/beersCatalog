package com.smesonero.beercatalog.ddbb.db_entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DbFoodPairing (

    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo (name = "idBeer") val idBeer: Int,
    @ColumnInfo (name = "pairingText") val pairingText : String
)