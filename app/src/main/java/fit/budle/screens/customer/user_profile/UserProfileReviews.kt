package fit.budle.screens.customer.user_profile

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import fit.budle.components.moleculas.BudleNavigationHeader
import fit.budle.navigation.routes.NavRoute

@Composable
fun UserProfileReviewsScreen(navController: NavHostController) {
    BudleNavigationHeader(
        textMessage = "Мои отзывы",
        route = NavRoute.UserProfile.route,
        navController = navController
    )
}