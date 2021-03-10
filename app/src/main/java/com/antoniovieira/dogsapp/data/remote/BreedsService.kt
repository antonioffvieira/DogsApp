package com.antoniovieira.dogsapp.data.remote

import com.antoniovieira.dogsapp.data.model.Breed
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface BreedsService {

    @GET("breeds")
    fun getBreeds(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): Observable<List<Breed>>

}