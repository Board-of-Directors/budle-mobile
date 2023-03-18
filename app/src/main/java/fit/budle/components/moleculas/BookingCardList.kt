package fit.budle.components.moleculas

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fit.budle.components.atoms.BudleBookingCard
import fit.budle.models.Booking

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun BudleBookingCardList(bookingList: MutableList<Booking>) {

    val listState = remember { bookingList }

    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 20.dp)
    ) {
        itemsIndexed(listState) { _, booking ->
            BudleBookingCard(booking = booking)
        }
    }
}