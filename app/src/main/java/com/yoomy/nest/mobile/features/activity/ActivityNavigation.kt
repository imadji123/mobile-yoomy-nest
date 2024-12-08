package com.yoomy.nest.mobile.features.activity

sealed class ActivityNavigationItem(val route: String) {
    data object History : ActivityNavigationItem(
        route = "activityHistory"
    )

    data object Detail : ActivityNavigationItem(
        route = "activityDetail"
    )
}