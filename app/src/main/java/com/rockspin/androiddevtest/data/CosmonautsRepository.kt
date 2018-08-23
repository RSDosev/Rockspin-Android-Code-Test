package com.rockspin.androiddevtest.data

import com.rockspin.androiddevtest.data.entities.CosmonautActivity
import com.rockspin.androiddevtest.data.remote.RemoteDataSource
import io.reactivex.Observable
import javax.inject.Inject

open class CosmonautsRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) : CosmonautsDataSource {

    override fun getActivities(): Observable<List<CosmonautActivity>> = remoteDataSource.getActivities()

}