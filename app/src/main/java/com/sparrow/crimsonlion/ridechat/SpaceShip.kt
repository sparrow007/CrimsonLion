package com.sparrow.crimsonlion.ridechat

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparrow.crimsonlion.R

@Composable
fun AndroidAlien(
    color: Color,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_launcher_foreground),
        contentDescription = null,
        colorFilter = ColorFilter.tint(color)
    )
}

@Composable
fun AndroidAliensGameOverBox() {

}


@Composable
fun AndroidAliensRows() {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top
    ) {
        AndroidAlien(color = Color.Green, modifier = Modifier.size(60.dp))
        AndroidAlien(color = Color.Red, modifier = Modifier.weight(1F, fill = true))
        AndroidAlien(color = Color.Blue, modifier = Modifier.size(70.dp))
    }

}

@Composable
fun AndroidAliensColumn() {
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AndroidAlien(color = Color.Green)
        AndroidAlien(color = Color.Red, modifier = Modifier.size(100.dp))
        AndroidAlien(color = Color.Blue)
    }

}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun AndroidAlienPreview() {
//    AndroidAliensRows()
//    AndroidAliensColumn()
    AndroidAliensGameOverBox()
}