package com.cryptoapp.ui.coin_home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cryptoapp.R
import com.cryptoapp.data.models.Coin
import com.cryptoapp.ui.coin_home.common.CategoryCoinListItem
import com.cryptoapp.ui.coin_home.common.CoinListItem
import com.cryptoapp.ui.common.CoinErrorScreen
import com.cryptoapp.ui.common.CoinLoadingScreen
import com.cryptoapp.ui.common.CustomAppBar
import com.cryptoapp.ui.common.CustomDivider
import com.cryptoapp.ui.nav.Screen

@Composable
fun CoinHomeScreen(
    navController: NavController,
    homeViewModel: CoinHomeViewModel = hiltViewModel()
){
    val state = homeViewModel.availableBooksState.collectAsState()
    when (val result = state.value) {
        is AvailableBooksState.Loading -> {
            CoinLoadingScreen()
        }

        is AvailableBooksState.Success -> {
            CoinHomeContent(
                viewModel = homeViewModel,
                navController = navController
            )
        }

        is AvailableBooksState.Failure -> {
            CoinErrorScreen(
                message = result.message
            )
        }
    }
}

@Composable
fun CoinHomeContent(
    navController: NavController,
    viewModel: CoinHomeViewModel
){
    val categoryListState = viewModel.categoryListState
    val coinListState = viewModel.coinListState
    val lazyRowState = rememberLazyListState()
    val lazyColumnState = rememberLazyListState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CustomAppBar(
            title = stringResource(id = R.string.app_name)
        )

        when(val data = categoryListState.value) {
            is CategoryListState.Loading -> { }
            is CategoryListState.Success -> {
                Text(
                    text = stringResource(id = R.string.clear_filter),
                    style = MaterialTheme.typography.caption,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .clickable {
                            viewModel.filterList()
                        }
                )

                LazyRow(
                    state = lazyRowState
                ) {
                    items(data.data) { category ->
                        CategoryCoinListItem(
                            category = category
                        ) { query ->
                            viewModel.filterList(query)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        Row(
            modifier = Modifier
                .padding(horizontal = 25.dp, vertical = 10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.available_currencies),
                style = MaterialTheme.typography.caption,
            )
            Text(
                text = stringResource(id = R.string.prices),
                style = MaterialTheme.typography.caption,
            )
        }

        Card(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(topStart = 20.dp,topEnd = 20.dp),
            elevation = 10.dp
        ) {

            when(val data = coinListState.value) {
                is CoinListState.Loading -> {
                    CircularProgressIndicator()
                }
                is CoinListState.Success -> {
                    LazyColumn(
                        state = lazyColumnState
                    ) {
                        items(
                            items = data.data,
                            key = { coin -> coin.id }
                        ){ coin ->
                            CoinListItem(
                                coin = coin
                            ) {
                                navController.navigate(Screen.CoinDetailScreen.withArgs(it.id))
                            }
                            CustomDivider()
                        }
                    }
                }
            }
        }
    }
}