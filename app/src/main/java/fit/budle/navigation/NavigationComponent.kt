package fit.budle.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import fit.budle.ui.screens.HomeScreen
import fit.budle.viewmodel.MainViewModel


@Composable
fun NavigationComponent(navController: NavHostController) {
    NavHost(
        navController = navController, startDestination = "home", modifier = Modifier.padding(10.dp)
    ) {
        composable("home") {
            val mainViewModel = viewModel<MainViewModel>()
            HomeScreen(navController, mainViewModel::getListOfEstablishments, mainViewModel::getListOfCategories)
        }
    }
}
