package fit.budle.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import fit.budle.screens.*
import fit.budle.screens.customer.user_profile.*
import fit.budle.screens.user_profile.*
import fit.budle.ui.screens.CardScreen
import fit.budle.ui.screens.HomeScreen
import fit.budle.ui.screens.OrderScreen
import fit.budle.viewmodel.BookViewModel
import fit.budle.viewmodel.MainViewModel

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun NavigationComponent(navController: NavHostController) {
    val mainViewModel = viewModel<MainViewModel>()
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                navController,
                mainViewModel::getListOfEstablishments,
                mainViewModel::getListOfCategories
            )
        }

        composable("card/{category}/{cardId}") {
            CardScreen(
                navController,
                (mainViewModel::getCard)(
                    it.arguments?.getString("category"),
                    it.arguments?.getString("cardId")
                )
            )
        }

        composable("order/{id}/{name}") {
            val bookViewModel = viewModel<BookViewModel>()
            OrderScreen(
                navController,
                it.arguments?.getString("id"),
                it.arguments?.getString("name"),
                bookViewModel::getOrder,
                bookViewModel::sendGuestCount,
                bookViewModel::sendData,
                bookViewModel::sendTime
            )
        }

        /*

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

        //<editor-fold desc="establishments">
        composable("main_page") { MainListScreen(navController) }
        composable("establishment_card") {
            navController.previousBackStackEntry?.arguments?.getParcelable<EstablishmentCard>("EST_KEY")
                ?.let {
                    EstablishmentCardScreen(navController, it)
                }
        }
        composable("booking_process") {
            navController.previousBackStackEntry?.arguments?.getParcelable<EstablishmentCard>("EST_KEY")
                ?.let {
                    BookingProcessScreen(navController, it)
                }
        }*/
        //</editor-fold>

        //<editor-fold desc="user_profile">
        composable("user_profile") { UserProfileScreen(navController) }
        //composable("user_profile_settings") { UserProfileSettingsScreen(navController) }
        //composable("user_profile_favorites") { UserProfileFavoritesScreen(navController) }
        composable("user_profile_bookings") {
            UserProfileBookingsScreenBackendConnected(
                navController,
                mainViewModel::getListOfOrders,
                mainViewModel::deleteOrderFromUser
            )
        }
        //composable("user_profile_reviews") { UserProfileReviewsScreen(navController) }
        //</editor-fold>*/
    }
}
