package fit.budle.components.moleculas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import fit.budle.components.atoms.cards.BudleBookingCard
import fit.budle.components.atoms.cards.BudleBusinessOrderCard
import fit.budle.models.Booking
import fit.budle.models.BookingStatus

@Composable
fun BudleBusinessOrderCardList(
        modifier: Modifier = Modifier,
        bookingList: MutableList<Booking>,
        filter: String = "Все"
) {
    Column(
            modifier = modifier.fillMaxWidth()
    ) {
        for (booking in bookingList) {

            val tagName = booking.infoList[0].second as BookingStatus

            if (!booking.isRejected.value) {
                when (filter) {
                    "Все" -> {
                        BudleBusinessOrderCard(booking = booking)
                    }
                    "Принятые" -> {
                        if (tagName == BookingStatus.CONFIRM) {
                            BudleBusinessOrderCard(booking = booking)
                        }
                    }
                    "В ожидании" -> {
                        if (tagName == BookingStatus.WAIT) {
                            BudleBusinessOrderCard(booking = booking)
                        }
                    }
                    "Отклонённые" -> {
                        if (tagName == BookingStatus.REJECT) {
                            BudleBusinessOrderCard(booking = booking)
                        }
                    }
                }
            }
        }
    }
}