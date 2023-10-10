package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.BottomBarScreen
import com.example.myapplication.screens.FavoriteScreen
import com.example.myapplication.screens.HomeScreen
import com.example.myapplication.screens.ReviewScreen
import com.example.myapplication.screens.SettingsScreen

@Composable
fun BottomNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route ){

        composable(route = BottomBarScreen.Home.route){
            HomeScreen()
        }

        composable(route = BottomBarScreen.Favorite.route){
            FavoriteScreen()
        }

        composable(route = BottomBarScreen.Review.route){
            ReviewScreen()
        }

        composable(route = BottomBarScreen.Settings.route){
            SettingsScreen()
        }
    }
}