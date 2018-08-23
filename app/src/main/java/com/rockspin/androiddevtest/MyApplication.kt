package com.rockspin.androiddevtest

import com.rockspin.androiddevtest.di.app.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MyApplication : DaggerApplication() {

    private val appComponent: AndroidInjector<MyApplication> by lazy {
        DaggerAppComponent.builder().create(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = appComponent
}
