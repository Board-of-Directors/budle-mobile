package fit.budle.ui.components.moleculas.card_lists

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import fit.budle.dto.order.Booking
import fit.budle.dto.order.BookingStatus
import fit.budle.ui.components.atoms.cards.BudleBusinessOrderCard

@Composable
fun BudleBusinessOrderCardList(
    modifier: Modifier = Modifier,
    bookingList: List<Booking>,
    filter: Int = 3,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        for (booking in bookingList) {
            if (booking.status == filter || filter == 3) {
                BudleBusinessOrderCard(booking = booking)
            }
        }
    }
}