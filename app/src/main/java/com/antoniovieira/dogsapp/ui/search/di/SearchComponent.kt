package com.antoniovieira.dogsapp.ui.search.di

import com.antoniovieira.dogsapp.ui.search.SearchFragment
import dagger.Subcomponent

@Subcomponent(modules = [SearchModule::class])
interface SearchComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): SearchComponent
    }

    fun inject(fragment: SearchFragment)

}