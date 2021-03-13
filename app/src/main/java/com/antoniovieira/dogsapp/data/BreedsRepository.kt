package com.antoniovieira.dogsapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.antoniovieira.dogsapp.data.model.Breed
import com.antoniovieira.dogsapp.data.pagination.BreedsPagingSource
import com.antoniovieira.dogsapp.data.remote.BreedsService
import io.reactivex.Flowable
import io.reactivex.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class BreedsRepository @Inject constructor(
    private val breedsService: BreedsService,
    private val pagingSource: BreedsPagingSource
) {

    @ExperimentalCoroutinesApi
    fun getBreeds(): Flowable<PagingData<Breed>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = { pagingSource }
        ).flowable
    }

    fun searchBreedByName(query: String): Single<List<Breed>> {
        return breedsService.searchBreedsByName(query)
    }

}