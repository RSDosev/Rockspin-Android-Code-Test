package com.rockspin.androiddevtest.usecases.transformers

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler

class TestTransformer<T> : Transformer<T>() {
    override fun apply(upstream: Observable<T>): ObservableSource<T> {
        return upstream.subscribeOn(Schedulers.trampoline()).observeOn(Schedulers.trampoline())
    }
}