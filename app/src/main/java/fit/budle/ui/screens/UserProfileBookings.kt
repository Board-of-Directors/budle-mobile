package fit.budle.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fit.budle.ui.components.BudleSearchBar
import fit.budle.dto.order.Booking
import fit.budle.dto.tag.active.RectangleActiveTag
import fit.budle.dto.tag.active.ordersTagList
import fit.budle.ui.components.atoms.headers.BudleNavigationHeader
import fit.budle.ui.components.moleculas.tag_list.BudleTagList

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun UserProfileBookingsScreenBackendConnected(
    navController: NavHostController,
    bookingsProvider: (Long, String?) -> (Array<Booking>),
    deletingProvider: (Long, Long) -> Unit
) {
    val tempUserId = 1L
    val currentType = remember {
        mutableStateOf(0)
    }
    Surface(Modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            BudleNavigationHeader(
                textMessage = "Мои брони",
                onClick = {
                    navController.popBackStack()
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
            BudleSearchBar()
            Spacer(modifier = Modifier.height(12.dp))
            BudleTagList(
                initialState = RectangleActiveTag("",-1),
                onValueChange = {},
                tagList = ordersTagList
            )
            /*BudleBookingCardList(
                bookingList = bookingsProvider(tempUserId, currentType.value),
                deletingProvider = deletingProvider
            )*/
        }
    }
}
