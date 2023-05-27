package fit.budle.navigation.graphs

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import fit.budle.ui.screens.business.creator.CreatorMainScreen
import fit.budle.ui.screens.business.creator.creator_profile.EstablishmentOrdersScreen
import fit.budle.ui.screens.business.creator.creator_profile.EstablishmentWorkersScreen

@SuppressLint("UnrememberedGetBackStackEntry")
@RequiresApi(Build.VERSION_CODES.P)
fun NavGraphBuilder.creatorAccountNavGraph(
    navHostController: NavHostController,
) {
    navigation(
        startDestination = "mainScreen",
        route = "businessAccount/"
    ) {
        composable("mainScreen") {
            CreatorMainScreen(navHostController = navHostController)
        }
        composable("workers/{establishmentId}") {
            it.arguments?.getString("establishmentId")?.let { string ->
                EstablishmentWorkersScreen(
                    navHostController = navHostController,
                    establishmentId = string.toInt()
                )
            }
        }
        composable("bookings/{establishmentId}") {
            it.arguments?.getString("establishmentId")?.let { string ->
                EstablishmentOrdersScreen(
                    navHostController = navHostController,
                    establishmentId = string.toInt()
                )
            }
        }
    }
}