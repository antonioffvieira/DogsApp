package com.antoniovieira.dogsapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava2.cachedIn
import com.antoniovieira.dogsapp.data.BreedsRepository
import com.antoniovieira.dogsapp.data.model.Breed
import io.reactivex.Flowable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val breedsRepository: BreedsRepository
): ViewModel() {

    @ExperimentalCoroutinesApi
    fun getBreeds(): Flowable<PagingData<Breed>> {
        return breedsRepository
            .getBreeds()
            .cachedIn(viewModelScope)
    }
}