package fit.budle.components.atoms

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import fit.budle.components.atoms.tags.BudleRateTag
import fit.budle.model.Booking
import fit.budle.models.BookingStatus
import fit.budle.ui.theme.*

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun BudleBookingCard(booking: Booking) {

    BookingCard(booking = booking)
    BookingInformation(booking = booking)

    BudleButton(
        onClick = {
            // FIXME: make delete user request here
        },
        topPadding = 0.dp,
        horizontalPadding = 0.dp,
        buttonText = "Удалить бронь",
        disabledButtonColor = lightBlue,
        activeButtonColor = fillPurple,
        disabledTextColor = fillPurple,
        activeTextColor = mainWhite
    )

}

@Composable
fun BookingCard(booking: Booking) {
    val gradient = Brush.verticalGradient(listOf(alphaBlack, alphaBlack))
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp),
        shape = RoundedCornerShape(15.dp)
    ) {
        Box(
            modifier = Modifier.height(120.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            if (booking.establishmentImage.image != null) {
                Image(
                    painter = booking.establishmentImage.image!!,
                    contentDescription = "Restaurant Card",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            }
            Box(
                Modifier
                    .matchParentSize()
                    .background(gradient)
            )
            Row(
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Row(
                        modifier = Modifier
                            .width(IntrinsicSize.Max)
                            .padding(bottom = 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = booking.establishment.category,
                            style = MaterialTheme.typography.labelSmall,
                            color = mainWhite,
                        )
                        Card(
                            modifier = Modifier.padding(horizontal = 7.dp),
                            shape = CircleShape
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(3.dp)
                                    .background(mainWhite)
                            )
                        }
                        Text(
                            text = booking.establishment.category,
                            style = MaterialTheme.typography.labelSmall,
                            color = mainWhite
                        )
                    }
                    Text(
                        text = booking.establishment.name,
                        style = MaterialTheme.typography.titleSmall,
                        color = mainWhite,
                    )
                }
                BudleRateTag(tag = booking.establishment.rating.toString())
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun BookingInformation(booking: Booking) {

    val statusColor = when (booking.status) {
        0 -> textGray
        1 -> backgroundSuccess
        2 -> backgroundError
        else -> throw IllegalStateException()
    }

    Column(Modifier.padding(horizontal = 20.dp)) {
        val infoList = HashMap<String, Any>()
        infoList["Статус"] = BookingStatus.create(booking.status)
        infoList["Дата"] = booking.date + booking.time
        infoList["Количество гостей"] = booking.guestCount
        infoList.forEach { pair ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max)
                    .padding(bottom = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier.width(94.dp),
                    text = pair.key,
                    style = MaterialTheme.typography.labelSmall,
                    color = textGray
                )
                if (pair.value is BookingStatus) {
                    Text(
                        modifier = Modifier
                            .padding(start = 25.dp)
                            .fillMaxWidth(),
                        text = (pair.value as BookingStatus).message,
                        style = MaterialTheme.typography.labelSmall,
                        color = statusColor,
                    )
                } else {
                    Text(
                        modifier = Modifier
                            .padding(start = 25.dp)
                            .fillMaxWidth(),
                        text = pair.value.toString(),
                        style = MaterialTheme.typography.labelSmall,
                        color = mainBlack
                    )
                }
            }
        }
    }
}