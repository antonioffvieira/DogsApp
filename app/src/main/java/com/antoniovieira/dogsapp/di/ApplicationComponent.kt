package com.antoniovieira.dogsapp.di

import android.content.Context
import com.antoniovieira.dogsapp.ui.home.di.HomeComponent
import com.antoniovieira.dogsapp.ui.search.di.SearchComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        NetworkModule::class,
        ViewModelBuilderModule::class,
        SubComponentsModule::class
    ]
)
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }

    fun homeComponent(): HomeComponent.Factory

    fun searchComponent(): SearchComponent.Factory

}