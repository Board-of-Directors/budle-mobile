package fit.budle.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import fit.budle.dto.tag.active.RectangleActiveTag
import fit.budle.dto.tag.active.ordersTagList
import fit.budle.event.customer.OrderListEvent
import fit.budle.ui.components.BudleBookingCardList
import fit.budle.ui.components.BudleSearchBar
import fit.budle.ui.components.atoms.headers.BudleNavigationHeader
import fit.budle.ui.components.moleculas.tag_list.BudleTagList
import fit.budle.viewmodel.customer.OrderListViewModel

@Composable
fun UserProfileBookingsScreenBackendConnected(
    navController: NavHostController,
    viewModel: OrderListViewModel = hiltViewModel(),
    ) {
    val tempUserId = 1L
    val currentType = remember {
        mutableStateOf(0)
    }
    viewModel.onEvent(OrderListEvent.getOrder(tempUserId, currentType.value))
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
                initialState = RectangleActiveTag("", -1),
                onValueChange = {viewModel.onEvent(OrderListEvent.getOrder(tempUserId, it.tagId))},
                tagList = ordersTagList
            )
            BudleBookingCardList(
                bookingList = viewModel.orders,
                viewModel
            )
        }
    }
}
