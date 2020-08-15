package com.smesonero.beercatalog.dinjection

import android.app.Application
import androidx.room.Room
import com.smesonero.beercatalog.ddbb.BeerDatabase
import com.smesonero.beercatalog.ddbb.dao.BeerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)

class DatabaseModule{

    @Provides
    @Singleton
    fun provideBeerDao(database: BeerDatabase):BeerDao{
        return database.beerDao()
    }

    @Provides
    @Singleton
    fun provideBeerDb(application: Application?):BeerDatabase{

        return Room.databaseBuilder(application!!, BeerDatabase::class.java, "Database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
}