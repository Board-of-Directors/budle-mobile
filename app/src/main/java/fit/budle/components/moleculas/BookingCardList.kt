package fit.budle.components.moleculas

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fit.budle.components.atoms.BudleBookingCard
import fit.budle.models.Booking

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun BudleBookingCardList(
    bookingList: MutableList<Booking>,
    filter: String = "Все"
) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 20.dp)
    ) {
        itemsIndexed(bookingList) { i, _ ->
            if (!bookingList[i].isRejected.value) {
                if (filter == "Все") {
                    BudleBookingCard(booking = bookingList[i])
                } else if (bookingList[i].establishmentDescription.type.startsWith(
                        filter.substring(0, filter.length - 2), true
                    )
                ) {
                    BudleBookingCard(booking = bookingList[i])
                }
            }
        }
    }
}