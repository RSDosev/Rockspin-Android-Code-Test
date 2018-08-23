package com.rockspin.androiddevtest.baseclasses

data class ViewState<D>(val isLoading: Boolean,
                            val data: D?,
                            val error: Throwable?) {

    companion object {
        fun <D> LOADING(): ViewState<D> = ViewState(true, null, null)
        fun <D> DATA(data: D?): ViewState<D> = ViewState(false, data, null)
        fun <D> ERROR(error: Throwable?): ViewState<D> = ViewState(false, null, error)
    }
}