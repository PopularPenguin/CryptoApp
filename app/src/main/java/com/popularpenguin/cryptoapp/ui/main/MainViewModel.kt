package com.popularpenguin.cryptoapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.popularpenguin.cryptoapp.data.model.Currency
import com.popularpenguin.cryptoapp.data.repository.Repository
import com.popularpenguin.cryptoapp.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val repository: Repository
) : ViewModel() {

    var uiState = MutableStateFlow<List<Currency>>(listOf())
        private set

    init {
        viewModelScope.launch {
            while (true) {
                getTickerData()
                delay(30_000L) // update every 30 seconds
            }
        }
    }

    private suspend fun getTickerData() {
        uiState.value = repository.getTicker(Constants.currencies)
    }
}