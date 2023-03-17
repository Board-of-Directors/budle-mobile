package fit.budle.screens.user_profile

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fit.budle.components.moleculas.BudleEstablishmentCardDescriptionList
import fit.budle.components.moleculas.BudleSearchBar
import fit.budle.components.moleculas.BudleTagList
import fit.budle.components.moleculas.ShowNavigationHeader
import fit.budle.models.establishmentDescriptionList
import fit.budle.navigation.NavRoute

@RequiresApi(Build.VERSION_CODES.N)
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
                route = "user_profile",
                navController = navController
            )
            BudleSearchBar()
            BudleTagList()
            BudleEstablishmentCardDescriptionList(
                establishmentDescription = establishmentDescriptionList
            )
        }
    }
}