package fit.budle.ui.screens.business.creator.creator_profile

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import fit.budle.dto.establishment.EstablishmentResponse
import fit.budle.dto.events.BookingEvent
import fit.budle.dto.order.Booking
import fit.budle.dto.tag.active.ordersTagList
import fit.budle.ui.components.atoms.headers.BudleNavigationHeader
import fit.budle.ui.components.budleTagList
import fit.budle.ui.components.moleculas.card_lists.BudleBusinessOrderCardList
import fit.budle.viewmodel.EstOrderListViewModel

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun EstablishmentOrdersScreen(
    navHostController: NavHostController,
    establishmentId: Int,
    viewModel: EstOrderListViewModel = hiltViewModel(),
) {

    var currentType by remember {
        mutableStateOf(3)
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            BudleNavigationHeader(
                textMessage = "Заказы",
                onClick = { navHostController.popBackStack() }
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {
                currentType = budleTagList(
                    tagList = ordersTagList
                )
            }
            BudleBusinessOrderCardList(
                bookingList = viewModel.state,
                filter = currentType
            )
        }
        viewModel.onEvent(BookingEvent.GetBookings(establishmentId))
    }
}