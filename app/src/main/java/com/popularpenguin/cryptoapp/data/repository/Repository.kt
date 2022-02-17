package com.popularpenguin.cryptoapp.data.repository

import com.popularpenguin.cryptoapp.data.api.ApiService
import com.popularpenguin.cryptoapp.data.model.Currency

class Repository(private val apiService: ApiService) {

    suspend fun getTicker(ids: List<String>): List<Currency> {
        return apiService.getTicker(idsCsv = ids.joinToString(separator = ","))
    }
}