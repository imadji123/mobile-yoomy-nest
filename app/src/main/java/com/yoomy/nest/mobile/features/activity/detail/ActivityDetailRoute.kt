package com.yoomy.nest.mobile.features.activity.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner

@Composable
fun ActivityDetailRoute(
    modifier: Modifier = Modifier,
    viewModel: ActivityDetailViewModel = hiltViewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        lifecycleOwner.lifecycle.addObserver(viewModel)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(viewModel)
        }
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = "Activity Detail Screen: ${viewModel.activityId}")
    }
}