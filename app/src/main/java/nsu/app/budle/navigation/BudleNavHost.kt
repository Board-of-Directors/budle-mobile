package nsu.app.budle.navigation

import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import nsu.app.budle.screens.*

sealed class NavRoute(val route: String) {
    object Start : NavRoute("start_screen")
    object Number : NavRoute("number_screen")
    object Code : NavRoute("code_screen")
    object Data : NavRoute("data_screen")
    object End : NavRoute("end_screen")
}

@Composable
fun BudleNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavRoute.Start.route) {
        composable(NavRoute.Start.route) { StartScreen(navController = navController) }
        composable(NavRoute.Number.route) {
            EnterAnimation {
                NumberScreen(navController = navController)
            }
        }
        composable(NavRoute.Code.route) { CodeScreen(navController = navController) }
        composable(NavRoute.Data.route) { DataScreen(navController = navController) }
        composable(NavRoute.End.route) { EndScreen(navController = navController) }
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
