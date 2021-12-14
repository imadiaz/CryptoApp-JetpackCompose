package com.cryptoapp.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.cryptoapp.R


@Composable
fun CustomAppBar(
    title: String = "",
    showBackButton: Boolean = false,
    navController: NavController? = null,
    color: Color = MaterialTheme.colors.surface,
    textColor: Color = Color.Unspecified
) {

    val textColor = if(textColor != Color.Unspecified) {
        textColor
    } else {
        Color.Unspecified
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(10.dp).background(color = color)
    ) {
        if (showBackButton) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_back_ios_new),
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .clickable {
                               navController?.popBackStack()
                    },
                colorFilter = ColorFilter.tint(textColor)
            )
        }

        Text(
            text = title.replaceFirstChar { it.uppercase() },
            style = MaterialTheme.typography.h4.copy(color = textColor),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = if(showBackButton) 30.dp else 0.dp),
            )
    }
}