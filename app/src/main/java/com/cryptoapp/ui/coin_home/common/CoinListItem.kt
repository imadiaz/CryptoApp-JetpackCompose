package com.cryptoapp.ui.coin_home.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.cryptoapp.R
import com.cryptoapp.data.models.Coin
import com.cryptoapp.data.models.getImageSource
import com.cryptoapp.data.models.getName
import com.cryptoapp.data.models.getSymbol
import com.cryptoapp.utils.formatAsCurrency
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage


@Composable
fun CoinListItem(
    coin: Coin,
    onClick: (Coin) -> Unit
){
    Card(
        modifier = Modifier
            .padding(10.dp)
            .clickable {
                onClick(coin)
            },
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            GlideImage(
                imageModel = coin.getImageSource(),
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                error = painterResource(id = R.drawable.ic_monetization_on),
                circularReveal = CircularReveal(duration = 350),
                requestOptions = {
                    RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                }
            )
            Spacer(modifier = Modifier.width(20.dp))
            Column {
                Text(
                    text = coin.getName(),
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = coin.getSymbol(),
                    style = MaterialTheme.typography.caption
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = coin.maximumPrice.toDouble().formatAsCurrency(),
                    style = MaterialTheme.typography.body2
                )
                Text(
                    text = coin.minimumPrice.toDouble().formatAsCurrency(),
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}