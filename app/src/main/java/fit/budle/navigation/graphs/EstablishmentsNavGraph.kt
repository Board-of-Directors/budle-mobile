package fit.budle.navigation.graphs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import fit.budle.models.BusinessEstablishmentModel
import fit.budle.models.EstablishmentCard
import fit.budle.navigation.routes.NavRoute
import fit.budle.navigation.routes.NestedGraphRoute
import fit.budle.screens.business.creator.CreatorMainScreen
import fit.budle.screens.business.creator.creator_profile.EstablishmentOrdersScreen
import fit.budle.screens.business.creator.creator_profile.EstablishmentWorkersScreen
import fit.budle.screens.customer.establishments.BookingProcessScreen
import fit.budle.screens.customer.establishments.EstablishmentCardScreen
import fit.budle.screens.customer.establishments.MainListScreen

@RequiresApi(Build.VERSION_CODES.N)
fun NavGraphBuilder.establishmentsNavGraph(
    navHostController: NavHostController
) {
    navigation(
        startDestination = NavRoute.MainPage.route,
        route = NestedGraphRoute.Establishments.route
    ) {
        composable(NavRoute.MainPage.route) { MainListScreen(navHostController) }
        composable(NavRoute.EstablishmentCard.route) {
            navHostController.previousBackStackEntry?.arguments?.getParcelable<EstablishmentCard>("EST_KEY")
                ?.let {
                    EstablishmentCardScreen(navHostController, it)
                }
        }
        composable(NavRoute.BookingProcess.route) {
            navHostController.previousBackStackEntry?.arguments?.getParcelable<EstablishmentCard>("EST_KEY")
                ?.let {
                    BookingProcessScreen(navHostController, it)
                }
        }
    }
}