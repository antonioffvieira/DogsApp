package com.antoniovieira.dogsapp.ui.search.di

import androidx.lifecycle.ViewModel
import com.antoniovieira.dogsapp.di.ViewModelKey
import com.antoniovieira.dogsapp.ui.search.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SearchModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindViewModel(viewModel: SearchViewModel): ViewModel

}