package com.cryptoapp.ui.coin_home.common


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.cryptoapp.R
import com.cryptoapp.data.models.Category
import com.cryptoapp.data.models.getImageSource
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun CategoryCoinListItem(
    category: Category,
    onClick: (String) -> Unit
){

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier =  Modifier
                .padding(10.dp)
                .clickable {
                    onClick(category.name)
                } ,
            elevation = 1.dp,
            shape = RectangleShape,
            backgroundColor = if (category.isSelected) MaterialTheme.colors.primary  else MaterialTheme.colors.surface
        ) {
            Column(
                modifier = Modifier.padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GlideImage(
                    imageModel = category.name.getImageSource(),
                    modifier = Modifier
                        .size(20.dp)
                        .clip(CircleShape),
                    error = painterResource(id = R.drawable.ic_monetization_on),
                    circularReveal = CircularReveal(duration = 350),
                    requestOptions = {
                        RequestOptions()
                            .override(256, 256)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                    }
                )

            }
        }

        Text(
            text = category.name.uppercase(),
            style = MaterialTheme.typography.overline.copy(fontWeight = FontWeight.Bold)
        )
    }
}