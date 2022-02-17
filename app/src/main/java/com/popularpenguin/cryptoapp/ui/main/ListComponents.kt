package com.popularpenguin.cryptoapp.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder
import coil.transform.CircleCropTransformation
import com.popularpenguin.cryptoapp.R
import com.popularpenguin.cryptoapp.data.model.Currency
import com.popularpenguin.cryptoapp.ui.theme.Gray300
import com.popularpenguin.cryptoapp.ui.theme.Green300
import com.popularpenguin.cryptoapp.ui.theme.Red300
import com.popularpenguin.cryptoapp.util.Format

@Composable
fun MainScreen() {
    val viewModel: MainViewModel = viewModel()
    val currencyList = viewModel.uiState.collectAsState().value
    // ImageLoader required for decoding SVG files
    // put here to avoid having to construct a new one for every list item
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .componentRegistry {
            add(SvgDecoder(LocalContext.current))
        }
        .build()

    LazyColumn {
        items(currencyList) { currency ->
            CryptoListItem(currency = currency, imageLoader = imageLoader)
        }
    }
}

@Composable
fun CryptoListItem(currency: Currency, imageLoader: ImageLoader) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(brush = Brush.horizontalGradient(colors = listOf(Color.White, Gray300)))
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CoinImage(currency = currency, imageLoader = imageLoader)
        CoinPrice(currency = currency)
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CoinImage(currency: Currency, imageLoader: ImageLoader) {
    Row {
        // required to wrap Image in a CompositionLocalProvider to use the ImageLoader for SVG files
        CompositionLocalProvider(LocalImageLoader provides imageLoader) {
            Image(
                modifier = Modifier.size(64.dp),
                painter = rememberImagePainter( // Coil loading the SVG from the network
                    data = currency.logoUrl,
                    builder = {
                        transformations(CircleCropTransformation())
                    }
                ),
                contentDescription = currency.name,
                contentScale = ContentScale.FillBounds
            )
        }

        Column(modifier = Modifier.padding(all = 8.dp)) {
            // currency name
            Text(
                text = currency.name ?: "",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            // currency symbol (abbreviation)
            Text(
                text = currency.symbol ?: "",
                fontSize = 12.sp,
                fontWeight = FontWeight.Light
            )
        }
    }
}

@Composable
fun CoinPrice(currency: Currency) {
    val percent = currency.oneDay.priceChangePct
    // formatted percent change
    val changePercent = Format.formatPercent(currency.oneDay.priceChangePct)
    // set red or green arrow depending on which direction the price is headed
    val trendingImageResId = percent?.toDoubleOrNull()?.let { value ->
        if (value < 0) R.drawable.down else R.drawable.up
    } ?: R.drawable.flat
    // set the color of the percentage
    val textColor = percent?.toDoubleOrNull()?.let { value ->
        if (value < 0) Red300 else Green300
    } ?: Color.Black

    Row(modifier = Modifier.padding(all = 8.dp)) {
        // trend direction image
        Image(
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.CenterVertically),
            painter = painterResource(id = trendingImageResId),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(24.dp))
        Column(modifier = Modifier.width(100.dp)) {
            // current price
            Text(
                modifier = Modifier.align(Alignment.End),
                text = Format.formatUS(currency.price),
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            // daily change percentage
            Text(
                modifier = Modifier.align(Alignment.End),
                text = changePercent,
                color = textColor,
                fontSize = 12.sp
            )
        }
    }
}