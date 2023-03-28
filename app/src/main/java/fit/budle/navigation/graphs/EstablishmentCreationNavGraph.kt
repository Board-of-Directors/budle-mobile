package fit.budle.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import fit.budle.navigation.routes.NavRoute
import fit.budle.navigation.routes.NestedGraphRoute
import fit.budle.screens.business.creator.ESTABLISHMENT_CREATION_FIRST
import fit.budle.screens.business.creator.EstablishmentCreationFirstScreen
import fit.budle.screens.business.creator.creation_process.EstablishmentCreationFourthScreen
import fit.budle.screens.business.creator.creation_process.EstablishmentCreationSecondScreen
import fit.budle.screens.business.creator.creation_process.EstablishmentCreationThirdScreen

fun NavGraphBuilder.establishmentCreationNavGraph(
    navHostController: NavHostController
) {
    navigation(
        startDestination = NavRoute.EstablishmentCreationFirst.route,
        route = NestedGraphRoute.EstablishmentCreation.route
    ) {
        composable(NavRoute.EstablishmentCreationFirst.route) {
            EstablishmentCreationFirstScreen(navHostController = navHostController)
        }
        composable(NavRoute.EstablishmentCreationSecond.route) {
            EstablishmentCreationSecondScreen(navHostController = navHostController)
        }
        composable(NavRoute.EstablishmentCreationThird.route) {
            EstablishmentCreationThirdScreen(navHostController = navHostController)
        }
        composable(NavRoute.EstablishmentCreationFourth.route) {
            EstablishmentCreationFourthScreen(navHostController = navHostController)
        }
    }
}