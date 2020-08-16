package com.smesonero.beercatalog.service

import android.util.Log
import com.smesonero.beercatalog.ddbb.dao.BeerDao
import com.smesonero.beercatalog.ddbb.db_entities.DbBeer
import com.smesonero.beercatalog.ddbb.db_entities.DbFoodPairing
import com.smesonero.beercatalog.domain_model.Beer
import com.smesonero.beercatalog.network_model.WsBeer
import java.lang.Exception
import javax.inject.Inject


class BeerRepository @Inject constructor(beerDao: BeerDao){

    var client: BeerService = createRetrofitWS()
    var beerDao = beerDao

    suspend fun getBeers(): List<Beer>
    {
        if (emptyDatabase()) {
            try {
                var firstPage = client.getFirstPage().body()
                Log.e("BEERREPO", "Obt  first page " + firstPage)
                var secondPage = client.getSecondPage().body()
                Log.e("BEERREPO", "Obt second page " + secondPage)

                var list: List<Beer> = generateList(firstPage, secondPage)
                insertBeers(list)

            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("repo", "error "+e.localizedMessage)
            }
        }
        return obtainFromBd()
    }

    private fun obtainFromBd(): List<Beer> {


        var listRet = mutableListOf<Beer>()
        var beersDb = beerDao.getAll()

        if (beersDb.size>0){
            Log.e("repo", "OBTAIN FRom exist")
            beersDb.forEach{

                var listPairingDb = beerDao.getFoodPairingsById(it.id.toString())

                var listPairing = mutableListOf<String>()
                listPairingDb.forEach{
                    listPairing.add(it.pairingText)
                }

                var beer= Beer(
                    it.id,
                    it.name,
                    it.tagline,
                    it.description,
                    it.image_url,
                    it.abv,
                    it.ibu,
                    listPairing.toList(),
                    it.available
                )
                listRet.add(beer)
            }
        }
        else{
            Log.e("repo", "OBTAIN FRom bd, empty")
        }
        return listRet.toList()
    }

    private fun insertBeers(list: List<Beer>) {

        list.forEach{
            var beerId = it.id
            var dbBeer = DbBeer(
                beerId,
                it.name,
                it.tagline,
                it.description,
                it.image_url,
                it.abv,
                it.ibu,
                it.available
            )
            beerDao.insert(dbBeer)

            it.foodPairing.forEach{
                var pairing = DbFoodPairing(
                    0,
                    beerId,
                    it
                )
                Log.e("REPO", "Insert pairing, id "+beerId +" texto "+it)
                beerDao.insertPairing(pairing)
            }
        }

    }

    private fun emptyDatabase(): Boolean {

        return beerDao.getAll().isEmpty()
    }

    private fun generateList(firstPage: List<WsBeer>?, secondPage: List<WsBeer>?): List<Beer> {

        var list : MutableList<Beer> = mutableListOf()
        if(firstPage!=null && firstPage.size>0){
            firstPage.toList().forEach{


                var beer = Beer(
                    it.id ,
                    it.name ?: "",
                    it.tagline ?: "",
                    it.description ?: "",
                    it.image_url ?: "",
                    it.abv ?: "",
                    it.ibu ?: "",
                    it.food_pairing ?: listOf(),
                    true
                )
                list.add(beer)
            }
        }
        if(secondPage!=null && secondPage.size>0){
            secondPage.forEach{
                var beer = Beer(
                    it.id ,
                    it.name ?: "",
                    it.tagline ?: "",
                    it.description ?: "",
                    it.image_url ?: "",
                    it.abv ?: "",
                    it.ibu ?: "",
                    it.food_pairing ?: listOf(),
                    true
                )
                list.add(beer)
            }
        }
        return list.toList();
    }

    fun getBeerById(idBeer: Int): Beer {
        var it = beerDao.getBeerById(idBeer.toString())
        var listPairingDb = beerDao.getFoodPairingsById(it.id.toString())

        var listPairing = mutableListOf<String>()
        listPairingDb.forEach{
            listPairing.add(it.pairingText)
        }
        return Beer(
            it.id ,
            it.name ?: "",
            it.tagline ?: "",
            it.description ?: "",
            it.image_url ?: "",
            it.abv ?: "",
            it.ibu ?: "",
            listPairing ?: listOf(),
            it.available
        )
    }

    fun updateBeer(beer: Beer, available: Boolean) {
        beerDao.update(available, beer.id)
    }
}