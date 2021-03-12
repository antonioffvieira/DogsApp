package com.antoniovieira.dogsapp

import android.app.Application
import com.antoniovieira.dogsapp.di.ApplicationComponent
import com.antoniovieira.dogsapp.di.DaggerApplicationComponent

class DogsApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent
            .factory()
            .create(applicationContext)
    }

}