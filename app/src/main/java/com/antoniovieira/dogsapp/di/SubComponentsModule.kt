package com.antoniovieira.dogsapp.di

import com.antoniovieira.dogsapp.ui.home.di.HomeComponent
import com.antoniovieira.dogsapp.ui.search.di.SearchComponent
import dagger.Module

@Module(
    subcomponents = [
        HomeComponent::class,
        SearchComponent::class
    ]
)
object SubComponentsModule