package fit.budle.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fit.budle.dto.order.Booking
import fit.budle.ui.components.atoms.cards.BudleBookingCard
import fit.budle.viewmodel.customer.OrderListViewModel

@Composable
fun BudleBookingCardList(
    bookingList: Array<Booking>, viewModel: OrderListViewModel
) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 20.dp)
    ) {
        itemsIndexed(bookingList) { i, _ ->
            BudleBookingCard(booking = bookingList[i], viewModel)
        }
    }
}
