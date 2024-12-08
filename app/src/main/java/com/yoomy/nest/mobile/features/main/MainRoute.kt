package com.yoomy.nest.mobile.features.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.yoomy.nest.mobile.features.activity.ActivityNavigationItem
import com.yoomy.nest.mobile.features.activity.ActivityRoute
import com.yoomy.nest.mobile.features.activity.detail.ActivityDetailRoute
import com.yoomy.nest.mobile.features.activity.history.ActivityHistoryRoute
import com.yoomy.nest.mobile.features.home.HomeRoute
import com.yoomy.nest.mobile.ui.theme.NestTheme

@Composable
fun MainRoute(
    navController: NavHostController,
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val isAppReady by viewModel.isAppReady.collectAsStateWithLifecycle()

    val currentBackStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry.value?.destination?.route

    val showBottomBar = currentDestination?.contains(ActivityNavigationItem.Detail.route)?.not() ?: true

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .padding(top = NestTheme.dimens.baseDimens.statusBarHeight),
        bottomBar = {
            AnimatedVisibility(
                visible = showBottomBar,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it })
            ) {
                MainNavigationBar(navController = navController)
            }
        }
    ) { innerPadding ->
        if (isAppReady) {
            NavHost(
                navController = navController,
                startDestination = MainNavigationItem.Home.route,
                modifier = Modifier.consumeWindowInsets(paddingValues = innerPadding)
            ) {
                composable(route = MainNavigationItem.Home.route) {
                    HomeRoute()
                }
                composable(route = MainNavigationItem.Activity.route) {
                    ActivityRoute(navController = navController)
                }
                composable(route = ActivityNavigationItem.History.route) {
                    ActivityHistoryRoute()
                }
                composable(
                    route = ActivityNavigationItem.Detail.route + "/{activityId}",
                    arguments = listOf(
                        navArgument(name = "activityId") {
                            type = NavType.StringType
                        }
                    )
                ) {
                    ActivityDetailRoute()
                }
            }
        }
    }
}