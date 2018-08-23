package com.rockspin.androiddevtest.data.remote

import com.rockspin.androiddevtest.data.CosmonautsDataSource
import com.rockspin.androiddevtest.data.entities.CosmonautActivity
import io.reactivex.Observable
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: APIService) : CosmonautsDataSource {

    override fun getActivities(): Observable<List<CosmonautActivity>> = apiService.getCosmonautActivities()

}