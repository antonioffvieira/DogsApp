package com.antoniovieira.dogsapp.ui.home.di

import com.antoniovieira.dogsapp.ui.home.HomeFragment
import dagger.Subcomponent

@Subcomponent(modules = [HomeModule::class])
interface HomeComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeComponent
    }

    fun inject(fragment: HomeFragment)

}