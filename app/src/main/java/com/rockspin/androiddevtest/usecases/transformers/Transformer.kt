package com.rockspin.androiddevtest.usecases.transformers

import io.reactivex.ObservableTransformer

abstract class Transformer<T> : ObservableTransformer<T, T>