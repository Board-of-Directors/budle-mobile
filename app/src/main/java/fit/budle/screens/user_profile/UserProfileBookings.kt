package fit.budle.screens.user_profile

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fit.budle.components.atoms.BudleSearchBar
import fit.budle.components.moleculas.BudleBookingCardList
import fit.budle.components.moleculas.BudleNavigationHeader
import fit.budle.components.moleculas.budleTagList
import fit.budle.models.bookingList
import fit.budle.models.tagList

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun UserProfileBookingsScreen(navController: NavHostController) {

    val bookingsState = remember { bookingList }
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
                textMessage = "Мои брони",
                route = "user_profile",
                navController = navController
            )
            BudleSearchBar()
            currentType.value = budleTagList(
                initialState = 0,
                tagList = tagList
            )
            BudleBookingCardList(
                bookingList = bookingsState,
                filter = currentType.value
            )
        }
    }
}