package fit.budle.screens.user_profile

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fit.budle.components.atoms.BudleSearchBar
import fit.budle.components.moleculas.BudleBookingCardListBackendConnected
import fit.budle.components.moleculas.BudleNavigationHeader
import fit.budle.components.moleculas.budleTagList
import fit.budle.model.Booking
import fit.budle.models.bookingList
import fit.budle.models.tagList

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun UserProfileBookingsScreenBackendConnected(
    navController: NavHostController,
    bookingsProvider: (Long?) -> (Array<Booking>)
) {
    
    val tempUserId = 1L
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
            Row(modifier = Modifier.padding(top = 12.dp)) {}
            currentType.value = budleTagList(
                initialState = 0,
                tagList = tagList
            )
            Row(modifier = Modifier.padding(top = 12.dp)) {}
            BudleBookingCardListBackendConnected(
                bookingList = bookingsProvider(tempUserId),
                filter = currentType.value
            )

        }
    }
}