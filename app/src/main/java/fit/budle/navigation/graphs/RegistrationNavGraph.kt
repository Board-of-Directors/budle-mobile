package fit.budle.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import fit.budle.navigation.routes.NavRoute
import fit.budle.navigation.routes.NestedGraphRoute
import fit.budle.screens.CodeScreen
import fit.budle.screens.DataScreen
import fit.budle.screens.EndScreen
import fit.budle.screens.NumberScreen
import fit.budle.screens.customer.onboarding.StartScreen

fun NavGraphBuilder.registrationNavGraph(
    navHostController: NavHostController
) {
    navigation(
        startDestination = NavRoute.Start.route,
        route = NestedGraphRoute.Registration.route
    ) {
        composable(NavRoute.Start.route) { StartScreen(navHostController) }
        composable(NavRoute.Data.route) { backStackEntry ->
            DataScreen(
                navHostController,
                backStackEntry.arguments?.getString("button_name")
            )
        }
        composable(NavRoute.Number.route) { NumberScreen(navHostController) }
        composable(NavRoute.Code.route) { CodeScreen(navHostController) }
        composable(NavRoute.End.route) { EndScreen(navHostController) }
    }
}
