package com.cryptoapp.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cryptoapp.R

@Composable
fun CoinErrorScreen(
    message: String?
) {
    val color = if(isSystemInDarkTheme()) Color.White else Color.Red

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_info),
            contentDescription = null,
            modifier = Modifier.size(40.dp),
            colorFilter = ColorFilter.tint(color)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = message ?: "Ups! something went wrong!",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            color = color
        )
    }
}