package com.smesonero.beercatalog.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.smesonero.beercatalog.domain_model.Beer
import com.smesonero.beercatalog.service.BeerRepository
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class BeerViewModel @ViewModelInject constructor(

    private val repository: BeerRepository

) : ViewModel(), LifecycleObserver{


    lateinit var beerLiveData: LiveData<List<Beer>>

    fun getLiveData() {
        beerLiveData = liveData(Dispatchers.IO) {
            try {
                val beers = repository.getBeers()
                emit(beers)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
    fun giveMeBeerById(id: Int): Beer {
        return repository.getBeerById(id)
    }

    fun updateAvailable(beer: Beer, available: Boolean) {

        repository.updateBeer(beer, available)
    }

    val selectedBeerLiveData = MutableLiveData<Beer>()
}