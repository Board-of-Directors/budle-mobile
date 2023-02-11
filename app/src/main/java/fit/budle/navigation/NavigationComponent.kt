package fit.budle.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import fit.budle.ui.screens.DetailScreen
import fit.budle.ui.screens.HomeScreen
import fit.budle.viewmodel.DetailViewModel
import fit.budle.viewmodel.MainViewModel


@Composable
fun NavigationComponent(navController: NavHostController) {
    NavHost(
        navController = navController, startDestination = "home"
    ) {
        composable("home") {
            val mainViewModel = viewModel<MainViewModel>()
            HomeScreen(navController, mainViewModel::getCode)
        }
        composable("details") {
            val detailsViewModel = viewModel<DetailViewModel>()
            DetailScreen(detailsViewModel::getDetailText)
        }
    }
}
