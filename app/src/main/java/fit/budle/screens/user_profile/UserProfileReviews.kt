package fit.budle.screens.user_profile

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import fit.budle.components.moleculas.BudleNavigationHeader

@Composable
fun UserProfileReviewsScreen(navController: NavHostController) {
    BudleNavigationHeader(
        textMessage = "Мои отзывы",
        route = "user_profile",
        navController = navController
    )
}