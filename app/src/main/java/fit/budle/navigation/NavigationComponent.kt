package fit.budle.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import fit.budle.ui.screens.*
import fit.budle.ui.screens.business.creator.creator_profile.EstablishmentWorkersScreen

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun NavigationComponent(navController: NavHostController) {
    // val mainViewModel = viewModel<MainViewModel>()
    NavHost(
        navController = navController,
        startDestination = "worker_list"
    ) {
        composable("worker_list") {
            EstablishmentWorkersScreen(navHostController = navController)
        }/*
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
        composable("user_profile") { UserProfileScreen(navController) }
        composable("user_profile_bookings") {
            UserProfileBookingsScreenBackendConnected(
                navController,
                mainViewModel::getListOfOrders,
                mainViewModel::deleteOrderFromUser
            )
        }*/
    }
}
