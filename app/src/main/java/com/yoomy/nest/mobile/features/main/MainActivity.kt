package com.yoomy.nest.mobile.features.main

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.yoomy.nest.mobile.commons.extensions.isTablet
import com.yoomy.nest.mobile.ui.theme.NestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the splash screen
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isAppReady.value.not()
            }
        }

        // Enable edge-to-edge display
        enableEdgeToEdge()

        if (!isTablet) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        setContent {
            val navController = rememberNavController()

            NestTheme {
                MainRoute(
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }
    }
}