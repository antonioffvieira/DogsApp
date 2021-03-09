package com.antoniovieira.dogsapp.di

import dagger.Component

@Component(
    modules = [
        ApplicationModule::class,
        NetworkModule::class
    ]
)
interface ApplicationComponent