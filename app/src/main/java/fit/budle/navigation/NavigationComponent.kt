package fit.budle.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import fit.budle.models.BusinessEstablishmentModel
import fit.budle.models.EstablishmentCard
import fit.budle.navigation.graphs.*
import fit.budle.navigation.routes.NavRoute
import fit.budle.navigation.routes.NestedGraphRoute
import fit.budle.screens.*
import fit.budle.screens.business.creator.CreatorMainScreen
import fit.budle.screens.business.creator.creator_profile.EstablishmentOrdersScreen
import fit.budle.screens.business.creator.creator_profile.EstablishmentWorkersScreen
import fit.budle.screens.customer.establishments.BookingProcessScreen
import fit.budle.screens.customer.establishments.EstablishmentCardScreen
import fit.budle.screens.customer.establishments.MainListScreen
import fit.budle.screens.customer.onboarding.StartScreen
import fit.budle.screens.customer.user_profile.*
import fit.budle.ui.screens.HomeScreen
import fit.budle.viewmodel.MainViewModel

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun NavigationComponent(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = NestedGraphRoute.UserProfile.route
    ) {
        composable("home") {
            val mainViewModel = viewModel<MainViewModel>()
            HomeScreen(
                navHostController,
                mainViewModel::getListOfEstablishments,
                mainViewModel::getListOfCategories
            )
        }
        registrationNavGraph(navHostController)
        establishmentsNavGraph(navHostController)
        userProfileNavGraph(navHostController)
        businessProfileNavGraph(navHostController)
        establishmentCreationNavGraph(navHostController)
    }
}
