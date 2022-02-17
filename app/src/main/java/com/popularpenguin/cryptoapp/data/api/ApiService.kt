package com.popularpenguin.cryptoapp.data.api

import com.popularpenguin.cryptoapp.data.model.Currency
import com.popularpenguin.cryptoapp.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("currencies/ticker")
    suspend fun getTicker(
        @Query("key") apiKey: String = Constants.API_KEY,
        @Query("ids") idsCsv: String
    ) : List<Currency>
}