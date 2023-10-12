package com.example.myapplication.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.BottomBarScreen
import com.example.myapplication.screens.FavoriteScreen
import com.example.myapplication.screens.HomeScreen
import com.example.myapplication.screens.ReviewScreen
import com.example.myapplication.screens.SearchScreen
import com.example.myapplication.screens.SettingsScreen

@Composable
fun BottomNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route ){

        composable(route = BottomBarScreen.Home.route){
            HomeScreen(navController)
        }

        composable(route = BottomBarScreen.Favorite.route){
            FavoriteScreen(navController)
        }

        composable(route = BottomBarScreen.Review.route){
            ReviewScreen(navController)
        }

        composable(route = BottomBarScreen.Settings.route){
            SettingsScreen(navController)
        }

        composable(route = BottomBarScreen.Search.route){
                SearchScreen(navController)
        }
    }
}