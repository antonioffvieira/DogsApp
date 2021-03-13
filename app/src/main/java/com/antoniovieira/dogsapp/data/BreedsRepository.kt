package com.antoniovieira.dogsapp.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.antoniovieira.dogsapp.data.model.Breed
import com.antoniovieira.dogsapp.data.pagination.BreedsPagingSource
import io.reactivex.Flowable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class BreedsRepository @Inject constructor(
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

}