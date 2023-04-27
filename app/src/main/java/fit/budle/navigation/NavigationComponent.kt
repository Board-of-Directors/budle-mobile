package fit.budle.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import fit.budle.navigation.graphs.establishmentCreationNavGraph
import fit.budle.ui.screens.*
import fit.budle.ui.screens.business.creator.CreatorMainScreen
import fit.budle.ui.screens.business.creator.creator_process.EstablishmentCreationFirstScreen
import fit.budle.ui.screens.business.creator.creator_process.EstablishmentCreationFourthScreen
import fit.budle.ui.screens.business.creator.creator_process.EstablishmentCreationSecondScreen
import fit.budle.ui.screens.business.creator.creator_process.EstablishmentCreationThirdScreen
import fit.budle.ui.screens.business.creator.creator_profile.EstablishmentOrdersScreen
import fit.budle.ui.screens.business.creator.creator_profile.EstablishmentWorkersScreen

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun NavigationComponent(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "ownerMain"
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
        establishmentCreationNavGraph(navController)
    }
}
