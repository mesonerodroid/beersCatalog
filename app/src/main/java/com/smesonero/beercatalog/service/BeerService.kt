package com.smesonero.beercatalog.service

import com.smesonero.beercatalog.network_model.WsBeer
import retrofit2.Response
import retrofit2.http.GET

interface BeerService{


    companion object {
        const val ENDPOINT = "https://api.punkapi.com/v2/"
    }


//    https://api.punkapi.com/v2/beers?page=1&per_page=50
    @GET ("beers?page=1&per_page=50")
    suspend fun getFirstPage() : Response<List<WsBeer>>

    @GET ("beers?page=2&per_page=50")
    suspend fun getSecondPage() : Response<List<WsBeer>>







}