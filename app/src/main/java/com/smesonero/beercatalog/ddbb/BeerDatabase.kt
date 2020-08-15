package com.smesonero.beercatalog.ddbb

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.RoomDatabase
import com.smesonero.beercatalog.ddbb.dao.BeerDao
import com.smesonero.beercatalog.ddbb.db_entities.DbBeer
import com.smesonero.beercatalog.ddbb.db_entities.DbFoodPairing


@Database(entities = arrayOf(DbBeer::class, DbFoodPairing::class),version=1)
abstract class BeerDatabase : RoomDatabase() {

    abstract fun beerDao(): BeerDao

}