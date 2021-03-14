package com.antoniovieira.dogsapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.antoniovieira.dogsapp.R
import com.antoniovieira.dogsapp.data.BreedsRepository
import com.antoniovieira.dogsapp.data.model.Breed
import com.antoniovieira.dogsapp.utils.ExceptionHelper
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

    private val _errorTitleAndMessage = MutableLiveData<Pair<Int, Int>>()
    val errorTitleAndMessage: LiveData<Pair<Int, Int>> = _errorTitleAndMessage

    fun searchBreedByName(query: String) {
        breedsRepository.searchBreedByName(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { handleSuccessResponse(it) },
                onError = { _errorTitleAndMessage.value = ExceptionHelper.getExceptionMessage(it) },
            )
            .addTo(compositeDisposables)
    }

    private fun handleSuccessResponse(breeds: List<Breed>) {
        if (breeds.isEmpty()) {
            _errorTitleAndMessage.value =
                Pair(R.string.no_data_error_title, R.string.no_data_error_description)
        } else {
            _breeds.value = breeds
        }
    }

    override fun onCleared() {
        compositeDisposables.clear()
        super.onCleared()
    }
}