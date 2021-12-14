package com.cryptoapp.ui.nav

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cryptoapp.ui.coin_detail.CoinDetailScreen
import com.cryptoapp.ui.coin_home.CoinHomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.CoinHomeScreen.route
    ) {
        composable(
            route = Screen.CoinHomeScreen.route
        ) {
            CoinHomeScreen(
                navController = navController
            )
        }

        composable(
            route = Screen.CoinDetailScreen.route + "/{coinId}"
        ) {
            CoinDetailScreen(
                navController = navController
            )
        }
    }
}