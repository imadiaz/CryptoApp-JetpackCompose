package com.cryptoapp.ui.common

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.palette.graphics.Palette
import com.cryptoapp.ui.coin_home.CoinHomeViewModel
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.palette.BitmapPalette


@Composable
fun TestPalette(){
//    val viewModel: CoinHomeViewModel = hiltViewModel()
//    //var palette by remember { mutableStateOf<Palette?>(null) }
//    val state = viewModel.list.value
//    Box(
//        modifier = Modifier.fillMaxSize(),
//    ) {
//        GlideImage(
//            imageModel = "https://cdn.hobbyconsolas.com/sites/navi.axelspringer.es/public/styles/855/public/media/image/2019/04/toy-story-4-poster.jpg"
//            ,bitmapPalette = BitmapPalette {
//                viewModel.palette.value = it
//                viewModel.load()
//            },
//            modifier = Modifier
//                .fillMaxSize(),
//            circularReveal = CircularReveal(duration = 550)
//        )
//        LazyRow(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceEvenly
//        ) {
//
//                items(state){ color ->
//                    Box(
//                        modifier = Modifier
//                            .background(color = Color(color ))
//                            .size(40.dp)
//                    )
//                }
//
//        }
//    }
}