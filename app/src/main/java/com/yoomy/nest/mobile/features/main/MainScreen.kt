package com.yoomy.nest.mobile.features.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.yoomy.nest.mobile.ui.theme.calculateMaxScreenWidth

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    val maxWidth = calculateMaxScreenWidth()

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(width = maxWidth)
        ) {
            Text(text = "Main Screen")
        }
    }
}