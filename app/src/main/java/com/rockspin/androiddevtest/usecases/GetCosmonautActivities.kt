package com.rockspin.androiddevtest.usecases

import com.rockspin.androiddevtest.baseclasses.UseCase
import com.rockspin.androiddevtest.data.CosmonautsRepository
import com.rockspin.androiddevtest.data.entities.CosmonautActivity
import com.rockspin.androiddevtest.usecases.transformers.Transformer
import io.reactivex.Observable

class GetCosmonautActivities(transformer: Transformer<List<CosmonautActivity>>,
                             private val repository: CosmonautsRepository) : UseCase<List<CosmonautActivity>>(transformer) {

    override fun createObservable(data: Map<String, Any>?): Observable<List<CosmonautActivity>> =
            repository.getActivities()
}