package com.yoomy.nest.mobile.features.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Assignment
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.yoomy.nest.mobile.R

sealed class MainNavigationItem(val route: String, val icon: ImageVector, val labelResId: Int) {
    data object Home : MainNavigationItem(
        route = "home",
        icon = Icons.Outlined.Home,
        labelResId = R.string.nav_home
    )

    data object Activity : MainNavigationItem(
        route = "activity",
        icon = Icons.AutoMirrored.Outlined.Assignment,
        labelResId = R.string.nav_activity
    )

    companion object {
        fun values() = listOf(Home, Activity)
    }
}

@Composable
fun MainNavigationBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier = modifier) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        MainNavigationItem.values().forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(imageVector = item.icon, contentDescription = stringResource(item.labelResId))
                },
                label = {
                    Text(text = stringResource(item.labelResId))
                },
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // re-selecting the same item
                        launchSingleTop = true
                        // Restore state when re-selecting a previously selected item
                        restoreState = true
                    }
                },
                selected = currentRoute == item.route
            )
        }
    }
}