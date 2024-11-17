package com.yoomy.nest.mobile.features.main

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.yoomy.nest.mobile.features.activity.ActivityRoute
import com.yoomy.nest.mobile.features.home.HomeRoute

@Composable
fun MainRoute(
    navController: NavHostController,
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val isAppReady by viewModel.isAppReady.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            MainNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        if (isAppReady) {
            NavHost(
                navController = navController,
                startDestination = MainNavigationItem.Home.route,
                modifier = Modifier.consumeWindowInsets(paddingValues = innerPadding)
            ) {
                composable(MainNavigationItem.Home.route) {
                    HomeRoute()
                }
                composable(MainNavigationItem.Activity.route) {
                    ActivityRoute()
                }
            }
        }
    }
}