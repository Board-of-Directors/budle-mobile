package fit.budle.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fit.budle.ui.details.SearchBar
import fit.budle.ui.details.BookingCardList
import fit.budle.ui.details.NavigationHeader
import fit.budle.ui.details.tagList
import fit.budle.model.Booking
import fit.budle.model.tag.active.ordersTagList

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun UserProfileBookingsScreenBackendConnected(
    navController: NavHostController,
    bookingsProvider: (Long, String?) -> (Array<Booking>),
    deletingProvider: (Long, Long) -> Unit
) {
    val tempUserId = 1L
    val currentType = remember {
        mutableStateOf("Все")
    }
    Surface(Modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            NavigationHeader(
                textMessage = "Мои брони",
                navController = navController
            )
            Spacer(modifier = Modifier.height(12.dp))
            SearchBar()
            Spacer(modifier = Modifier.height(12.dp))
            currentType.value = tagList(
                initialState = 0,
                tagList = ordersTagList
            ).tagName
            BookingCardList(
                bookingList = bookingsProvider(tempUserId, currentType.value),
                deletingProvider = deletingProvider
            )
        }
    }
}
