package com.smesonero.beercatalog.service

import com.google.gson.GsonBuilder
import com.smesonero.beercatalog.service.BeerService.Companion.ENDPOINT
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



public fun createRetrofitWS(): BeerService {

    val webservice by lazy {
        Retrofit.Builder()
            .baseUrl(ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(BeerService::class.java)
    }
    return webservice
}


