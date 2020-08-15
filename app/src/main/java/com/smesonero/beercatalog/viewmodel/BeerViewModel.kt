package com.smesonero.beercatalog.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.smesonero.beercatalog.service.BeerRepository
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class BeerViewModel @ViewModelInject constructor(

    private val repository: BeerRepository

) : ViewModel(), LifecycleObserver{

    val beerLiveData = liveData(Dispatchers.IO) {
        try{
            val beers = repository.getBeers()
            Log.e("VIEWMODEL", "emit beers "+beers.size)
            emit(beers)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}