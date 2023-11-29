package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.BottomBarScreen
import com.example.myapplication.data.viewModel.UserViewModel
import com.example.myapplication.screens.CamaraScreen
import com.example.myapplication.screens.FavoriteScreen
import com.example.myapplication.screens.HomeScreen
import com.example.myapplication.screens.ReviewScreen
import com.example.myapplication.screens.SearchScreen
import com.example.myapplication.screens.SettingsScreen
import com.example.myapplication.screens.UpdatePasswordScreen

@Composable
fun BottomNavGraph(navController: NavHostController, userViewModel: UserViewModel = viewModel()){
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
            SettingsScreen(navController = navController,userViewModel=userViewModel)
        }

        composable(route = BottomBarScreen.Search.route){
            SearchScreen(navController)
        }

        composable(route = BottomBarScreen.Camara.route){
            CamaraScreen(navController = navController,userViewModel=userViewModel)
        }

        composable(route = BottomBarScreen.UpdatePassword.route){
            UpdatePasswordScreen(navController = navController,userViewModel=userViewModel)
        }
    }
}