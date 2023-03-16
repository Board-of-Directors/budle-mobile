package fit.budle.screens.user_profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fit.budle.components.moleculas.ShowNavigationHeader
import fit.budle.navigation.NavRoute

@Composable
fun UserProfileBookingsScreen(navController: NavHostController) {
    Surface(Modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(top = 30.dp)
        ) {
            ShowNavigationHeader(
                textMessage = "Мои брони",
                route = NavRoute.UserProfile.route,
                navController = navController
            )
        }
    }
}