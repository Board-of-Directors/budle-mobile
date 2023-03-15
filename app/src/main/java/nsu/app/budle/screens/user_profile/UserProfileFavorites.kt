package nsu.app.budle.screens.user_profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import nsu.app.budle.components.BudleSearchBar
import nsu.app.budle.components.moleculas.BudleTagList
import nsu.app.budle.components.moleculas.ShowNavigationHeader
import nsu.app.budle.navigation.NavRoute

@Composable
fun UserProfileFavoritesScreen(navController: NavHostController) {
    Surface(Modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(top = 30.dp)
        ) {
            ShowNavigationHeader(
                textMessage = "Избраное",
                route = NavRoute.UserProfile.route,
                navController = navController
            )
            BudleSearchBar()
            BudleTagList()
        }
    }
}