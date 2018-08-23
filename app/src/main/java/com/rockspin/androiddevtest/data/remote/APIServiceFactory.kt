package com.rockspin.androiddevtest.data.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object APIServiceFactory {

    fun build() =
            buildRetrofit()
                    .create(APIService::class.java)

    private fun buildRetrofit(): Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(buildGsonParser()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    private fun buildGsonParser(): Gson =
            GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    .create()
}