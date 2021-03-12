package com.antoniovieira.dogsapp.di

import com.antoniovieira.dogsapp.ui.home.HomeComponent
import dagger.Module

@Module(
    subcomponents = [
        HomeComponent::class
    ]
)
object SubComponentsModule