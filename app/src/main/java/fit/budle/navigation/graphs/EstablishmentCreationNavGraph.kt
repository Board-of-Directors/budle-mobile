package fit.budle.navigation.graphs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import fit.budle.ui.screens.business.creator.creator_process.*

@RequiresApi(Build.VERSION_CODES.P)
fun NavGraphBuilder.establishmentCreationNavGraph(
    navHostController: NavHostController,
) {
    navigation(
        startDestination = "firstStep",
        route = "ownerMain/establishmentCreation/"
    ) {
        composable("firstStep") {
            EstablishmentCreationFirstScreen(
                navHostController = navHostController,
                viewModel = hiltViewModel()
            )
        }
        composable("secondStep") {
            EstablishmentCreationSecondScreen(
                navHostController = navHostController,
                viewModel = hiltViewModel(
                    navHostController.previousBackStackEntry!!
                )
            )
        }
        composable("thirdStep") {
            EstablishmentCreationThirdScreen(
                navHostController = navHostController,
                viewModel = hiltViewModel(
                    navHostController.previousBackStackEntry!!
                )
            )
        }
        composable("fourthStep") {
            EstablishmentCreationFourthScreen(
                navHostController = navHostController,
                viewModel = hiltViewModel(
                    navHostController.previousBackStackEntry!!
                )
            )
        }
        composable("fifthStep") {
            EstablishmentCreationFifthScreen(
                navHostController = navHostController,
                viewModel = hiltViewModel(
                    navHostController.previousBackStackEntry!!
                )
            )
        }
    }
}