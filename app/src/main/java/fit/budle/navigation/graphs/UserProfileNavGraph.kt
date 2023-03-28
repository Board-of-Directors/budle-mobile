package fit.budle.navigation.graphs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import fit.budle.navigation.routes.NavRoute
import fit.budle.navigation.routes.NestedGraphRoute
import fit.budle.screens.customer.user_profile.*

@RequiresApi(Build.VERSION_CODES.N)
fun NavGraphBuilder.userProfileNavGraph(
    navHostController: NavHostController
) {
    navigation(
        startDestination = NavRoute.UserProfile.route,
        route = NestedGraphRoute.UserProfile.route
    ) {
        composable(NavRoute.UserProfile.route) { UserProfileScreen(navHostController) }
        composable(NavRoute.UserProfileSettings.route) { UserProfileSettingsScreen(navHostController) }
        composable(NavRoute.UserProfileFavorites.route) { UserProfileFavoritesScreen(navHostController) }
        composable(NavRoute.UserProfileBookings.route) { UserProfileBookingsScreen(navHostController) }
        composable(NavRoute.UserProfileReviews.route) { UserProfileReviewsScreen(navHostController) }
    }
}