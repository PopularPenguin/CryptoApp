package com.popularpenguin.cryptoapp.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.popularpenguin.cryptoapp.data.model.Currency
import com.popularpenguin.cryptoapp.data.model.OneDay
import com.popularpenguin.cryptoapp.ui.theme.CryptoAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CryptoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CryptoAppTheme {
        val currency = Currency(
            currency = "BTC",
            id = "BTC",
            price = "33400.1234",
            date = "",
            timestamp = "",
            symbol = "BTC",
            name = "Bitcoin",
            logoUrl = "",
            rank = "1",
            oneDay = OneDay(priceChange = "234", priceChangePct = "+0.10%")
        )

        // ... test any composable here
    }
}