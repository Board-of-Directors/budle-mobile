package fit.budle.navigation.graphs

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import fit.budle.ui.screens.business.creator.creator_process.*

@SuppressLint("UnrememberedGetBackStackEntry")
@RequiresApi(Build.VERSION_CODES.P)
fun NavGraphBuilder.establishmentCreationNavGraph(
    navHostController: NavHostController,
) {
    navigation(
        startDestination = "mapStep",
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
                    navHostController.getBackStackEntry("firstStep")
                )
            )
        }
        composable("thirdStep") {
            EstablishmentCreationThirdScreen(
                navHostController = navHostController,
                viewModel = hiltViewModel(
                    navHostController.getBackStackEntry("firstStep")
                )
            )
        }
        composable("fourthStep") {
            EstablishmentCreationFourthScreen(
                navHostController = navHostController,
                viewModel = hiltViewModel(
                    navHostController.getBackStackEntry("firstStep")
                )
            )
        }
        composable("mapStep") {
            EstablishmentCreationMapScreen(
                navHostController = navHostController,
                viewModel = hiltViewModel(
                    // navHostController.getBackStackEntry("firstStep")
                )
            )
        }
        composable("fifthStep") {
            EstablishmentCreationFifthScreen(
                navHostController = navHostController,
                viewModel = hiltViewModel(
                    navHostController.getBackStackEntry("firstStep")
                )
            )
        }
    }
}