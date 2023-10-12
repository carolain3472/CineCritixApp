package com.example.myapplication

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(

    val route: String,
    val title: Int,
    val icon: ImageVector
){
    object Home: BottomBarScreen(
        route = "HOME",
        title = R.string.home,
        icon = Icons.Default.Home
    )

    object Favorite: BottomBarScreen(
        route = "FAVORITES",
        title = R.string.favorite,
        icon = Icons.Default.Favorite
    )

    object Review: BottomBarScreen(
        route = "REVIEWS",
        title = R.string.reviews,
        icon = Icons.Default.List
    )

    object Settings: BottomBarScreen(
        route = "SETTINGS",
        title = R.string.settings,
        icon = Icons.Default.Settings
    )

    object Search: BottomBarScreen(
        route = "SEARCH",
        title = R.string.search,
        icon = Icons.Default.Search
    )
}

