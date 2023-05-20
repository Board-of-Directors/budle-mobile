package fit.budle.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import fit.budle.navigation.graphs.establishmentCreationNavGraph
import fit.budle.ui.screens.*
import fit.budle.ui.screens.business.creator.CreatorMainScreen
import fit.budle.ui.screens.business.creator.creator_profile.EstablishmentOrdersScreen
import fit.budle.ui.screens.business.creator.creator_profile.EstablishmentWorkersScreen
import fit.budle.viewmodel.customer.MainViewModel

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun NavigationComponent(navController: NavHostController) {
    val mainViewModel: MainViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = "main" //TODO Стартовое состояние
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
        establishmentCreationNavGraph(navController)
        composable("user_profile") {
            UserProfileScreen(
                navController = navController,
            )
        }
        composable("user_profile_bookings") {
            UserProfileBookingsScreenBackendConnected(
                navController = navController,
            )
        }
    }
}
