package com.cryptoapp.ui.nav

sealed class Screen(val route: String) {
    object CoinHomeScreen: Screen("coin_home_screen")
    object CoinDetailScreen: Screen("coin_detail_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/${arg}")
            }
        }
    }
}