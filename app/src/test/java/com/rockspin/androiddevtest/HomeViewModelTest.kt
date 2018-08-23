package com.rockspin.androiddevtest

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.nhaarman.mockito_kotlin.*
import com.rockspin.androiddevtest.baseclasses.ViewState
import com.rockspin.androiddevtest.data.CosmonautsRepository
import com.rockspin.androiddevtest.data.entities.CosmonautActivity
import com.rockspin.androiddevtest.home.HomeViewModel
import com.rockspin.androiddevtest.usecases.GetCosmonautActivities
import com.rockspin.androiddevtest.usecases.transformers.TestTransformer
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.TestScheduler
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.junit.MockitoJUnitRunner
import java.util.*
import org.mockito.Mockito.inOrder
import org.mockito.InOrder



@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    // A JUnit Test Rule that swaps the background executor used by
    // the Architecture Components with a different one which executes each task synchronously.
    // You can use this rule for your host side tests that use Architecture Components.
    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    // Test rule for making the RxJava to run synchronously in unit test
    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    private val repository = mock<CosmonautsRepository>()
    private val stateObserver = mock<Observer<ViewState<List<CosmonautActivity>>>>()

    private val dummyActivities = listOf(
            CosmonautActivity("Purpose 1", Date()),
            CosmonautActivity("Purpose 2", Date()),
            CosmonautActivity("Purpose 3", Date()))
    lateinit var getCosmonautActivities: GetCosmonautActivities
    private val viewModel by lazy { HomeViewModel(getCosmonautActivities) }

    @Before
    fun setup() {
        reset(stateObserver, repository)
        whenever(repository.getActivities()).thenReturn(Observable.just(dummyActivities))
        getCosmonautActivities = GetCosmonautActivities(TestTransformer(), repository)
    }

    @Test
    fun testLoadingActivities() {
        viewModel.viewState().observeForever(stateObserver)

        val expectedDataState = ViewState.DATA(dummyActivities)
        verify(stateObserver).onChanged(expectedDataState)
    }

    @Test
    fun testReverseActivitiesOrder() {
        val viewStateLiveData = viewModel.viewState()
        viewStateLiveData.observeForever(stateObserver)

        val expectedDataState = ViewState.DATA(dummyActivities)
        verify(stateObserver).onChanged(expectedDataState)

        viewModel.reverseActivitiesOrder()
        val expectedReversedDataState = ViewState.DATA(dummyActivities.reversed())
        verify(stateObserver).onChanged(expectedReversedDataState)
    }
}
