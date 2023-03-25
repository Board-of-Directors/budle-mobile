package fit.budle.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import fit.budle.models.BusinessEstablishmentModel
import fit.budle.navigation.routes.NavRoute
import fit.budle.navigation.routes.NestedGraphRoute
import fit.budle.screens.business.creator.CreatorMainScreen
import fit.budle.screens.business.creator.creator_profile.EstablishmentOrdersScreen
import fit.budle.screens.business.creator.creator_profile.EstablishmentWorkersScreen

fun NavGraphBuilder.businessProfileNavGraph(
    navHostController: NavHostController
) {
    navigation(
        startDestination = NavRoute.BusinessMain.route,
        route = NestedGraphRoute.BusinessProfile.route
    ) {
        composable(NavRoute.BusinessMain.route) { CreatorMainScreen(navHostController) }
        composable(NavRoute.EstablishmentSettings.route) {
            CreatorMainScreen(navHostController)
        }
        composable(NavRoute.EstablishmentOrders.route) {
            navHostController.previousBackStackEntry?.arguments?.getParcelable<BusinessEstablishmentModel>(
                "BUS_KEY"
            )
                ?.let {
                    EstablishmentOrdersScreen(navHostController, it)
                }
        }
        composable(NavRoute.EstablishmentWorkers.route) {
            navHostController.previousBackStackEntry?.arguments?.getParcelable<BusinessEstablishmentModel>(
                "BUS_KEY"
            )
                ?.let {
                    EstablishmentWorkersScreen(navHostController, it)
                }
        }
    }
}