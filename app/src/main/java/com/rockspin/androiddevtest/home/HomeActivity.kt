package com.rockspin.androiddevtest.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.transition.TransitionManager
import android.widget.Toast
import com.rockspin.androiddevtest.R
import com.rockspin.androiddevtest.baseclasses.ViewState
import com.rockspin.androiddevtest.data.entities.CosmonautActivity
import com.rockspin.androiddevtest.util.hide
import com.rockspin.androiddevtest.util.show
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class HomeActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: HomeViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
    }

    private val activitiesAdapter = EVActivityAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.viewState().observe(this, Observer { it ->
            it?.run {
                renderViewState(this)
            }
        })

        initUI()
    }

    private fun initUI() {
        recyclerView.adapter = activitiesAdapter
        DividerItemDecoration(this, DividerItemDecoration.VERTICAL).run {
            setDrawable(resources.getDrawable(R.drawable.list_divider))
            recyclerView.addItemDecoration(this)
        }

        changeSortingButton.setOnClickListener {
            viewModel.reverseActivitiesOrder()
        }
    }

    private fun renderViewState(viewState: ViewState<List<CosmonautActivity>>) {
        renderLoading(viewState.isLoading)

        viewState.data?.let {
            renderData(it)
        }
        viewState.error?.let {
            renderError(it)
        }
    }

    private fun renderLoading(isLoading: Boolean) {
        TransitionManager.beginDelayedTransition(rootLayout)
        if (isLoading) {
            loadingView.show()
            recyclerView.hide()
            changeSortingButton.hide()
        } else {
            loadingView.hide()
            recyclerView.show()
            changeSortingButton.show()
        }
    }

    private fun renderError(error: Throwable) {
        Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
        changeSortingButton.hide()
    }

    private fun renderData(activities: List<CosmonautActivity>) {
        activitiesAdapter.items = activities
    }
}
