package fit.budle.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import fit.budle.navigation.graphs.establishmentCreationNavGraph
import fit.budle.navigation.graphs.registrationNavGraph
import fit.budle.ui.screens.MainScreen
import fit.budle.ui.screens.OrderScreen
import fit.budle.ui.screens.UserProfileBookingsScreenBackendConnected
import fit.budle.ui.screens.business.creator.CreatorMainScreen
import fit.budle.ui.screens.business.creator.creator_profile.EstablishmentOrdersScreen
import fit.budle.ui.screens.business.creator.creator_profile.EstablishmentWorkersScreen
import fit.budle.ui.screens.customer.CardScreen
import fit.budle.ui.screens.customer.MapScreen
import fit.budle.ui.screens.customer.UserProfileScreen
import fit.budle.viewmodel.customer.MainViewModel

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun NavigationComponent(navController: NavHostController) {
    val mainViewModel: MainViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = "ownerMain/establishmentCreation/"
    ) {
        composable("ownerMain") {
            CreatorMainScreen(navHostController = navController)
        }
        composable("ownerMain/workers/{establishmentId}") {
            it.arguments?.getString("establishmentId")?.let { string ->
                EstablishmentWorkersScreen(
                    navHostController = navController,
                    establishmentId = string.toInt()
                )
            }
        }
        composable("ownerMain/bookings/{establishmentId}") {
            it.arguments?.getString("establishmentId")?.let { string ->
                EstablishmentOrdersScreen(
                    navHostController = navController,
                    establishmentId = string.toInt()
                )
            }
        }
        composable("main") {
            MainScreen(
                navHostController = navController,
                mainViewModel
            )
        }
        composable("orderCreateMap/{establishmentId}") {
            it.arguments?.getString("establishmentId")?.let { establishmentId ->
                MapScreen(
                    navHostController = navController,
                    establishmentId = establishmentId,
                    viewModel = hiltViewModel()
                )
            }
        }
        composable("card") {
            CardScreen(
                navHostController = navController,
                mainViewModel
            )
        }
        composable("orderCreate/{establishmentId}/{establishmentName}") {
            OrderScreen(
                navHostController = navController,
                it.arguments?.getString("establishmentId"),
                it.arguments?.getString("establishmentName"),
            )
        }
        registrationNavGraph(navController)
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
