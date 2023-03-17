package fit.budle.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fit.budle.screens.*
import fit.budle.screens.main_page.MainListScreen
import fit.budle.screens.onboarding.StartScreen
import fit.budle.screens.user_profile.*

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun BudleNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavRoute.UserProfile.route) {
        composable(NavRoute.Start.route) { StartScreen(navController = navController) }
        composable(NavRoute.Number.route) {
            EnterAnimation {
                NumberScreen(navController = navController)
            }
        }
        composable(NavRoute.Code.route) { CodeScreen(navController = navController) }
        composable(NavRoute.Data.route) { backStackEntry ->
            DataScreen(
                navController = navController,
                backStackEntry.arguments?.getString("button_name")
            )
        }
        composable(NavRoute.End.route) { EndScreen(navController = navController) }
        composable(NavRoute.MainList.route) { MainListScreen(navController = navController) }
        composable(NavRoute.UserProfile.route) { UserProfileScreen(navController = navController) }
        composable(NavRoute.UserProfileSettings.route) { UserProfileSettingsScreen(navController = navController) }
        composable(NavRoute.UserProfileFavorites.route) { UserProfileFavoritesScreen(navController = navController) }
        composable(NavRoute.UserProfileBookings.route) { UserProfileBookingsScreen(navController = navController) }
        composable(NavRoute.UserProfileReviews.route) { UserProfileReviewsScreen(navController = navController) }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun EnterAnimation(content: @Composable () -> Unit) {
    AnimatedVisibility(
        visible = true,
        enter = slideInVertically(
            initialOffsetY = { -40 }
        ) + expandVertically(
            expandFrom = Alignment.Top
        ) + fadeIn(initialAlpha = 0.3f),
        exit = slideOutVertically() + shrinkVertically() + fadeOut(),
        content = content,
        initiallyVisible = false
    )
}
