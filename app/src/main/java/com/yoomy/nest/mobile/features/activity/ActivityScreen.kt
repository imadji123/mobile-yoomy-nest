package com.yoomy.nest.mobile.features.activity

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ActivityScreen(
    onActivityClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(text = "Activity Screen")

        Button(
            onClick = {
                onActivityClick("id-123-abc-789")
            }
        ) {
            Text(text = "Go to activity detail")
        }
    }
}