package com.antoniovieira.dogsapp.di

import android.content.Context
import com.antoniovieira.dogsapp.DogsApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(
    val app: DogsApplication
) {

    @Provides
    @Singleton
    fun provideApp(): DogsApplication = app

    @Provides
    @Singleton
    fun provideAppContext(app: DogsApplication): Context = app

}