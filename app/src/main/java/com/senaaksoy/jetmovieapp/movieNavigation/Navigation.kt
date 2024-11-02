package com.senaaksoy.jetmovieapp.movieNavigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.senaaksoy.jetmovieapp.movieScreens.DetailScreen
import com.senaaksoy.jetmovieapp.movieScreens.MainScreen


@Composable
fun Navigation(){
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(Screen.MainScreen.route){
            MainScreen(navController = navController)
        }
        composable(Screen.DetailScreen.route +"/{movie}",
            arguments = listOf(navArgument(name = "movie"){type= NavType.IntType})) {
            backStackEntry ->
            val movieNameId = backStackEntry.arguments?.getInt("movie")
            DetailScreen(navController = navController, movieNameId)

        }

    }
}