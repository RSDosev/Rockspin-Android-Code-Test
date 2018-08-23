package com.rockspin.androiddevtest.data.remote

import com.rockspin.androiddevtest.data.entities.CosmonautActivity
import io.reactivex.Observable
import retrofit2.http.GET

internal const val BASE_URL = "https://data.nasa.gov/resource/"

interface APIService {

    /**
     * Perform a call to fetch a list of Extra-Vehicular Cosmonaut Activity starting from 1965
     * @return The Retrofit response as observable
     */
    @GET("9kcy-zwvn.json?\$order=date&\$limit=20")
    fun getCosmonautActivities(): Observable<List<CosmonautActivity>>
}
