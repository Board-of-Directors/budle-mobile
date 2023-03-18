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
import fit.budle.components.atoms.BudleBookingCard
import fit.budle.components.atoms.BudleInfoTag
import fit.budle.components.atoms.BudleSearchBar
import fit.budle.components.moleculas.BudleBookingCardList
import fit.budle.components.moleculas.BudleNavigationHeader
import fit.budle.components.moleculas.BudleTagList
import fit.budle.models.InfoTag
import fit.budle.models.bookingList
import fit.budle.models.bookingTagList

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun UserProfileBookingsScreen(navController: NavHostController) {
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
            BudleTagList()
            BudleBookingCardList(bookingList = bookingList)
        }
    }
}