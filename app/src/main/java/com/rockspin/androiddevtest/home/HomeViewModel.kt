package com.rockspin.androiddevtest.home

import android.arch.lifecycle.MutableLiveData
import com.rockspin.androiddevtest.baseclasses.BaseViewModel
import com.rockspin.androiddevtest.baseclasses.ViewState
import com.rockspin.androiddevtest.data.entities.CosmonautActivity
import com.rockspin.androiddevtest.usecases.GetCosmonautActivities
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val getCosmonautActivities: GetCosmonautActivities) : BaseViewModel() {

    private val stateLiveData: MutableLiveData<ViewState<List<CosmonautActivity>>> = MutableLiveData()

    fun viewState(): MutableLiveData<ViewState<List<CosmonautActivity>>> {
        if (stateLiveData.value == null)
            getCosmonautActivitiesFromTheAPI()

        return stateLiveData
    }

    private fun getCosmonautActivitiesFromTheAPI() {

        addDisposable(getCosmonautActivities.observe(null)
                .map { ViewState.DATA(it) }
                .onErrorReturn { ViewState.ERROR(it) }
                .startWith(ViewState.LOADING())
                .subscribe{ it ->
                    stateLiveData.value = it
                })
    }

    fun reverseActivitiesOrder() {
        stateLiveData.value = ViewState.DATA(stateLiveData.value?.data?.reversed())
    }
}