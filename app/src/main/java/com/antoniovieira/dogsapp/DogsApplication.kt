package com.antoniovieira.dogsapp

import android.app.Application
import com.antoniovieira.dogsapp.di.ApplicationComponent
import com.antoniovieira.dogsapp.di.ApplicationModule
import com.antoniovieira.dogsapp.di.DaggerApplicationComponent

class DogsApplication : Application() {

    lateinit var mApplicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        mApplicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

}