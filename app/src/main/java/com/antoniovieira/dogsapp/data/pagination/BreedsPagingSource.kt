package com.antoniovieira.dogsapp.data.pagination

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.antoniovieira.dogsapp.data.model.Breed
import com.antoniovieira.dogsapp.data.remote.BreedsService
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BreedsPagingSource @Inject constructor(
    private val service: BreedsService
) : RxPagingSource<Int, Breed>() {

    companion object {
        private const val DEFAULT_PAGE_INDEX = 1
        private const val LIMIT = 10
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Breed>> {
        val page = params.key ?: DEFAULT_PAGE_INDEX
        return service.getBreeds(page, LIMIT)
            .subscribeOn(Schedulers.io())
            .map { toLoadResult(it, page) }
            .onErrorReturn { LoadResult.Error(it) }
    }

    private fun toLoadResult(breeds: List<Breed>, page: Int): LoadResult<Int, Breed> {
        return LoadResult.Page(
            data = breeds,
            prevKey = if (page == 1) null else page - 1,
            nextKey = if (breeds.isEmpty()) null else page + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Breed>): Int? {
        return state.anchorPosition
    }
}