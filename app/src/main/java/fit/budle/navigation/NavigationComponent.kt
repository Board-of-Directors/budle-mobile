package fit.budle.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import fit.budle.models.EstablishmentCard
import fit.budle.screens.*
import fit.budle.screens.establishments.EstablishmentCard
import fit.budle.screens.establishments.MainListScreen
import fit.budle.screens.onboarding.StartScreen
import fit.budle.screens.user_profile.*
import fit.budle.ui.screens.HomeScreen
import fit.budle.viewmodel.MainViewModel

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun NavigationComponent(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "user_profile",
        modifier = Modifier.padding(10.dp)
    ) {
        composable("home") {
            val mainViewModel = viewModel<MainViewModel>()
            HomeScreen(
                navController,
                mainViewModel::getListOfEstablishments,
                mainViewModel::getListOfCategories
            )
        }

        //<editor-fold desc="onboarding">
        composable("start_screen") { StartScreen(navController) }
        composable("data_screen") { backStackEntry ->
            DataScreen(
                navController,
                backStackEntry.arguments?.getString("button_name")
            )
        }
        composable("number_screen") { NumberScreen(navController) }
        composable("code_screen") { CodeScreen(navController) }
        composable("end_screen") { EndScreen(navController) }
        //</editor-fold>

        //<editor-fold desc="user_profile">
        composable("main_page") { MainListScreen(navController) }
        composable("establishment_card") {
            navController.previousBackStackEntry?.arguments?.getParcelable<EstablishmentCard>("EST_KEY")
                ?.let {
                    EstablishmentCard(navController, it)
                }
        }
        //</editor-fold>

        //<editor-fold desc="user_profile">
        composable("user_profile") { UserProfileScreen(navController) }
        composable("user_profile_settings") { UserProfileSettingsScreen(navController) }
        composable("user_profile_favorites") { UserProfileFavoritesScreen(navController) }
        composable("user_profile_bookings") { UserProfileBookingsScreen(navController) }
        composable("user_profile_reviews") { UserProfileReviewsScreen(navController) }
        //</editor-fold>
    }
}
