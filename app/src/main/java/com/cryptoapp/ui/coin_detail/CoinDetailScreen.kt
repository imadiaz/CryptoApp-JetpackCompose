package com.cryptoapp.ui.coin_detail

import android.content.res.Configuration
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.cryptoapp.R
import com.cryptoapp.data.models.*
import com.cryptoapp.ui.common.CoinErrorScreen
import com.cryptoapp.ui.common.CoinLoadingScreen
import com.cryptoapp.ui.common.CustomAppBar
import com.cryptoapp.ui.common.CustomDivider
import com.cryptoapp.ui.theme.CryptoAppTheme
import com.cryptoapp.utils.formatAsCurrency
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.palette.BitmapPalette

@Composable
fun CoinDetailScreen(
    navController: NavController,
    viewModel : CoinDetailViewModel = hiltViewModel()
) {
    CoinDetailContent(
        navController = navController,
        viewModel = viewModel
    )
}

@Composable
fun CoinDetailContent(
    navController: NavController,
    viewModel: CoinDetailViewModel
){
    val title = viewModel.titleCoinDetail.collectAsState()
    val state = viewModel.tickerState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().background(color = Color.Black)
    ) {

        CustomAppBar(
            title = title.value,
            showBackButton = true,
            navController = navController,
            color = Color.Black,
            textColor = Color.White
        )

        when(val result = state.value) {
            is TickerState.Loading -> {
                CoinLoadingScreen()
            }
            is TickerState.Success -> {
                CoinTickerContent(
                    viewModel = viewModel,
                    coinDetail = result.data
                )
            }
            is TickerState.Failure -> {
                CoinErrorScreen(
                    message = result.message
                )
            }
        }

        Card(
            elevation = 10.dp,
            modifier = Modifier
                .fillMaxSize().padding(top = 8.dp)
            ,
            shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp)
        ) {
            CoinOrderContent(
                viewModel = viewModel
            )
        }

    }
}


@Composable
fun CoinTickerContent(
    viewModel: CoinDetailViewModel,
    coinDetail: CoinDetail
) {
    val color = viewModel.mainColor

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = coinDetail.last.toDouble().formatAsCurrency(),
            style = MaterialTheme.typography.body1.copy(
                color = Color.White,
                fontWeight = FontWeight.Bold
            ),
        )
        Text(
            text = "Last price",
            color = Color(color.value),
            style = MaterialTheme.typography.caption
        )

        Spacer(modifier = Modifier.height(15.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = coinDetail.high.toDouble().formatAsCurrency(),
                    style = MaterialTheme.typography.body1.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = "Hight",
                    color = Color(color.value),
                    style = MaterialTheme.typography.caption
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = coinDetail.low.toDouble().formatAsCurrency(),
                    style = MaterialTheme.typography.body1.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )

                )
                Text(
                    text = "Low",
                    color = Color(color.value),
                    style = MaterialTheme.typography.caption
                )
            }
        }

        GlideImage(
            imageModel = coinDetail.getImageSource(),
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            error = painterResource(id = R.drawable.ic_monetization_on),
            circularReveal = CircularReveal(duration = 350),
            requestOptions = {
                RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
            },
            bitmapPalette = BitmapPalette {
                viewModel.setPalette(it)
            }
        )
    }
}

@Composable
fun CoinOrderContent(
    viewModel: CoinDetailViewModel
){
    val state = viewModel.orderState.collectAsState()
    val color = viewModel.mainColor

    when(val result = state.value) {
        is OrderState.Loading -> {
            CoinLoadingScreen()
        }

        is OrderState.Success -> {
            CoinOrderList(
                askList = result.data.asks,
                color = Color(color.value)
            )
        }
        is OrderState.Failure -> {
            CoinErrorScreen(
                message = result.message
            )
        }
    }


}


@Composable
fun CoinOrderList(
    askList: List<Ask>,
    color: Color
){
    Column(
        modifier = Modifier.padding(20.dp)
    ) {

        LazyColumn {
            items(askList) { ask ->

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = 0.dp
                ) {
                    Row(
                        modifier = Modifier.padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = ask.getName(),
                                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
                            )
                            Text(
                                text = "Coin",
                                style = MaterialTheme.typography.caption
                            )
                        }

                        CustomDivider(isVertical = true, color = color)

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = ask.price.toDouble().formatAsCurrency(),
                                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
                            )
                            Text(
                                text = "Price",
                                style = MaterialTheme.typography.caption
                            )
                        }

                        CustomDivider(isVertical = true, color = color)

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = ask.amount.take(8),
                                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
                            )
                            Text(
                                text = "Amount",
                                style = MaterialTheme.typography.caption
                            )
                        }
                    }
                }

            }

        }
    }
}

//@Preview(showBackground = true,uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CryptoAppTheme {
        Surface(color = MaterialTheme.colors.background) {
            CoinDetailScreen(
                navController = rememberNavController()
            )
        }
    }
}