package fit.budle.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import fit.budle.navigation.graphs.creatorAccountNavGraph
import fit.budle.navigation.graphs.establishmentCreationNavGraph
import fit.budle.navigation.graphs.registrationNavGraph
import fit.budle.ui.screens.MainScreen
import fit.budle.ui.screens.OrderScreen
import fit.budle.ui.screens.UserProfileBookingsScreenBackendConnected
import fit.budle.ui.screens.customer.CardScreen
import fit.budle.ui.screens.customer.MapScreen
import fit.budle.ui.screens.customer.UserProfileScreen

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun NavigationComponent(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") {
            MainScreen(
                navHostController = navController,
                viewModel = hiltViewModel()
            )
        }
        composable("card") {
            CardScreen(
                navHostController = navController,
                viewModel = hiltViewModel(
                    navController.getBackStackEntry("main")
                )
            )
        }
        composable("orderCreation") {
            OrderScreen(
                navHostController = navController,
                orderViewModel = hiltViewModel(),
                mainViewModel = hiltViewModel(
                    navController.getBackStackEntry("main")
                )
            )
        }
        composable("orderCreation/map") {
            MapScreen(
                navHostController = navController,
                orderViewModel = hiltViewModel(
                    navController.getBackStackEntry("orderCreation")
                ),
                mainViewModel = hiltViewModel(
                    navController.getBackStackEntry("main")
                )
            )
        }

        registrationNavGraph(navController)
        creatorAccountNavGraph(navController)
        establishmentCreationNavGraph(navController)

        composable("userProfile") {
            UserProfileScreen(
                navController = navController,
                viewModel = hiltViewModel()
            )
        }
        composable("userProfile/bookings") {
            UserProfileBookingsScreenBackendConnected(
                navController = navController,
            )
        }
    }
}
