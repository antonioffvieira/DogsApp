package com.antoniovieira.dogsapp.ui.home.di

import androidx.lifecycle.ViewModel
import com.antoniovieira.dogsapp.di.ViewModelKey
import com.antoniovieira.dogsapp.ui.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindViewModel(viewModel: HomeViewModel): ViewModel

}