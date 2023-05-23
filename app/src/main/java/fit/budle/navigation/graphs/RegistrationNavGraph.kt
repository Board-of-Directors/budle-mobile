package fit.budle.navigation.graphs

import android.annotation.SuppressLint
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import fit.budle.ui.screens.registration.*

@SuppressLint("UnrememberedGetBackStackEntry")
fun NavGraphBuilder.registrationNavGraph(
    navHostController: NavHostController,
) {
    navigation(
        startDestination = "startScreen",
        route = "registration/"
    ) {
        composable("startScreen") {
            StartScreen(
                navHostController = navHostController,
                viewModel = hiltViewModel()
            )
        }
        composable("dataScreen") {
            DataScreen(
                navHostController = navHostController,
                viewModel = hiltViewModel(
                    navHostController.getBackStackEntry("startScreen")
                )
            )
        }
        composable("numberScreen") {
            NumberScreen(
                navHostController = navHostController,
                viewModel = hiltViewModel(
                    navHostController.getBackStackEntry("startScreen")
                )
            )
        }
        composable("codeScreen") {
            CodeScreen(
                navHostController = navHostController,
                viewModel = hiltViewModel(
                    navHostController.getBackStackEntry("startScreen")
                )
            )
        }
        composable("endScreen") {
            EndScreen(
                navHostController = navHostController,
                viewModel = hiltViewModel(
                    navHostController.getBackStackEntry("startScreen")
                )
            )
        }
    }
}