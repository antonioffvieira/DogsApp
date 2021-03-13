package com.antoniovieira.dogsapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.antoniovieira.dogsapp.data.BreedsRepository
import com.antoniovieira.dogsapp.data.model.Breed
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val breedsRepository: BreedsRepository
) : ViewModel() {

    private val compositeDisposables = CompositeDisposable()

    private val _breeds = MutableLiveData<List<Breed>>()
    val breeds: LiveData<List<Breed>> = _breeds

    fun searchBreedByName(query: String) {
        breedsRepository.searchBreedByName(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { _breeds.value = it },
                onError = { handleError(it)},
            )
            .addTo(compositeDisposables)
    }

    private fun handleError(throwable: Throwable) {
        // TODO
        //  Handle error and show a popup
    }

    override fun onCleared() {
        compositeDisposables.clear()
        super.onCleared()
    }
}