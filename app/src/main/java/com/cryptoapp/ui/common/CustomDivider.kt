package com.cryptoapp.ui.common

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun CustomDivider(
    modifier: Modifier = Modifier,
    color: Color = Color(0XFFF1F1F1),
    isVertical: Boolean = false
){
    if(isVertical) {
        Divider(
            color = color,
            modifier = modifier
                .width(1.dp)
                .height(50.dp)
        )
    } else {
        Divider(color = color, modifier = modifier)
    }

}