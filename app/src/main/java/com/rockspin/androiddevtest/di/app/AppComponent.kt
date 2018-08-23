/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rockspin.androiddevtest.di.app

import com.rockspin.androiddevtest.MyApplication
import com.rockspin.androiddevtest.di.home.HomeActivityModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Suppress("unused")
@Singleton
@Component(
        modules = [
            AndroidInjectionModule::class,
            AndroidSupportInjectionModule::class,
            AppModule::class,
            ViewModelsModule::class,
            UseCasesModule::class,
            HomeActivityModule::class]
)
interface AppComponent : AndroidInjector<MyApplication>{
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MyApplication>()
}
