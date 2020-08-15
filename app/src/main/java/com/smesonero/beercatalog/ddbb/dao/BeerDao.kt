package com.smesonero.beercatalog.ddbb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.smesonero.beercatalog.ddbb.db_entities.DbBeer
import com.smesonero.beercatalog.ddbb.db_entities.DbFoodPairing

@Dao
interface BeerDao{


    @Query("SELECT * FROM DbBeer")
    fun getAll(): List<DbBeer>

    @Query("DELETE FROM DbBeer")
    fun deleteAll()

    @Insert
    fun insert (vararg beers :DbBeer)


    @Query("SELECT * FROM DbFoodPairing WHERE idBeer = :id ")
    fun getFoodPairingsById(id:String): List<DbFoodPairing>

    @Insert
    fun insertPairing (vararg pairing :DbFoodPairing)
}