package fit.budle.ui.details

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fit.budle.ui.details.BookingCard
import fit.budle.model.Booking

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun BookingCardList(
    bookingList: Array<Booking>,
    deletingProvider: (Long, Long) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 20.dp)
    ) {
        itemsIndexed(bookingList) { i, _ ->
            BookingCard(booking = bookingList[i], deletingProvider = deletingProvider)
        }
    }
}
