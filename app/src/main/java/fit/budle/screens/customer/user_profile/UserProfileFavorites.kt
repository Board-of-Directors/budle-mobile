package fit.budle.screens.customer.user_profile

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fit.budle.components.atoms.inputs.BudleSearchBar
import fit.budle.components.moleculas.*
import fit.budle.models.establishmentDescriptionList
import fit.budle.models.tagList

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun UserProfileFavoritesScreen(navController: NavHostController) {

    val currentType = remember {
        mutableStateOf("Все")
    }

    Surface(Modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(top = 30.dp)
        ) {
            BudleNavigationHeader(
                textMessage = "Избраное",
                onClick = {
                    navController.popBackStack()
                }
            )
            BudleSearchBar()
            currentType.value = budleTagList(
                initialState = 0,
                tagList = tagList
            )
            BudleEstablishmentCardDescriptionList(
                establishmentDescription = establishmentDescriptionList,
                filter = currentType.value
            )
        }
    }
}