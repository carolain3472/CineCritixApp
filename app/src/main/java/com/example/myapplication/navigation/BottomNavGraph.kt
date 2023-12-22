package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.BottomBarScreen
import com.example.myapplication.data.viewModel.MoviesSeriesViewModel
import com.example.myapplication.data.viewModel.UserViewModel
import com.example.myapplication.screens.CamaraScreen
import com.example.myapplication.screens.FavoriteScreen
import com.example.myapplication.screens.HomeScreen
import com.example.myapplication.screens.IconoScreen
import com.example.myapplication.screens.MovieInfoScreen
import com.example.myapplication.screens.ReviewScreen
import com.example.myapplication.screens.SearchFilterScreen
import com.example.myapplication.screens.SearchScreen
import com.example.myapplication.screens.SettingsScreen
import com.example.myapplication.screens.UpdatePasswordScreen

@Composable
fun BottomNavGraph(navController: NavHostController, userViewModel: UserViewModel = viewModel(), moviesSeriesViewModel: MoviesSeriesViewModel = viewModel()){
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route ){

        composable(route = BottomBarScreen.Home.route){
            HomeScreen(navController= navController, moviesSeriesViewModel= moviesSeriesViewModel, userViewModel=userViewModel)
        }

        composable(route = BottomBarScreen.Favorite.route){
            FavoriteScreen(navController= navController, moviesSeriesViewModel= moviesSeriesViewModel, userViewModel=userViewModel)
        }

        composable(route = BottomBarScreen.Review.route){
            ReviewScreen(navController)
        }

        composable(route = BottomBarScreen.Settings.route){
            SettingsScreen(navController = navController,userViewModel=userViewModel)
        }

        composable(route = BottomBarScreen.Search.route){
            SearchScreen(navController= navController, moviesSeriesViewModel= moviesSeriesViewModel)
        }

        composable(route = BottomBarScreen.SearchFilter.route){
            SearchFilterScreen(navController= navController, moviesSeriesViewModel= moviesSeriesViewModel)
        }

        composable(route = BottomBarScreen.MovieInfo.route){
            MovieInfoScreen(navController= navController, moviesSeriesViewModel= moviesSeriesViewModel, userViewModel=userViewModel)
        }

        composable(route = BottomBarScreen.Camara.route){
            CamaraScreen(navController = navController,userViewModel=userViewModel)
        }

        composable(route = BottomBarScreen.UpdatePassword.route){
            UpdatePasswordScreen(navController = navController,userViewModel=userViewModel)
        }

        composable(route= BottomBarScreen.ElegirAvatar.route){
            IconoScreen(navController = navController,userViewModel=userViewModel)
        }
    }
}