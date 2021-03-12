package com.antoniovieira.dogsapp.data

import com.antoniovieira.dogsapp.data.remote.BreedsService
import javax.inject.Inject

class BreedsRepository @Inject constructor(
    private val service: BreedsService
) {

}