package com.rockspin.androiddevtest.baseclasses

import com.rockspin.androiddevtest.usecases.transformers.Transformer
import io.reactivex.Observable

abstract class UseCase<T>(private val transformer: Transformer<T>) {

    // TODO: ideally there should be also UseCaseSingle, UseCaseMaybe and UseCaseCompletable
    // but I am skipping them for simplicity
    abstract fun createObservable(data: Map<String, Any>? = null): Observable<T>

    open fun observe(withData: Map<String, Any>? = null): Observable<T> {
        return createObservable(withData).compose(transformer)
    }

}