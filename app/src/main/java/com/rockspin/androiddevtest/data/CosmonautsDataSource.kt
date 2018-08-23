package com.rockspin.androiddevtest.data

import com.rockspin.androiddevtest.data.entities.CosmonautActivity
import io.reactivex.Observable

interface CosmonautsDataSource {

    fun getActivities(): Observable<List<CosmonautActivity>>
}