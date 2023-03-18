package fit.budle.components.atoms

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fit.budle.models.Booking
import fit.budle.models.BookingStatus
import fit.budle.models.InfoTag
import fit.budle.models.bookingList
import fit.budle.ui.theme.*

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun BudleBookingCard(booking: Booking) {

    BookingCard(booking = booking)
    InfoTagList(tagList = booking.tags)
    BookingInformation(booking = booking)

    if (booking.infoList[0].second as BookingStatus
        != BookingStatus.REJECT
    ) {
        BudleButton(
            onClick = {
                booking.isRejected.value = !booking.isRejected.value
            },
            topPadding = 0.dp,
            horizontalPadding = 0.dp,
            buttonText = "Отменить бронь",
            disabledButtonColor = lightBlue,
            activeButtonColor = fillPurple,
            disabledTextColor = fillPurple,
            activeTextColor = mainWhite
        )
    }
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
            Image(
                painter = painterResource(id = booking.establishmentDescription.imgID),
                contentDescription = "Restaurant Card",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
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
                            text = booking.establishmentDescription.type,
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
                            text = booking.establishmentDescription.subtype,
                            style = MaterialTheme.typography.labelSmall,
                            color = mainWhite
                        )
                    }
                    Text(
                        text = booking.establishmentDescription.name,
                        style = MaterialTheme.typography.titleSmall,
                        color = mainWhite,
                    )
                }
                RateCard(rate = booking.establishmentDescription.rate)
            }
        }
    }
}

@Preview
@Composable
fun ShowBookingcard() {
    BookingCard(booking = bookingList[0])
}

@Composable
fun RateCard(rate: Double) {
    Card(
        colors = CardDefaults.cardColors(Color.White),
        modifier = Modifier
            .width(55.dp)
            .height(28.dp),
        shape = RoundedCornerShape(5.dp),
    ) {
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Center
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = rate.toString(),
                style = MaterialTheme.typography.bodyMedium,
                color = mainBlack,
            )
        }
    }
}

@Composable
fun InfoTagList(tagList: MutableList<InfoTag>) {
    LazyRow(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 20.dp)
            .fillMaxWidth()
    ) {
        itemsIndexed(tagList) { _, tag ->
            BudleInfoTag(infoTag = tag)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun BookingInformation(booking: Booking) {

    val status = booking.infoList[0].second as BookingStatus
    val statusColor = when (status.value) {
        1 -> textGray
        2 -> backgroundSuccess
        3 -> backgroundError
        else -> throw IllegalStateException()
    }

    Column(Modifier.padding(horizontal = 20.dp)) {
        booking.infoList.forEach { pair ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max)
                    .padding(bottom = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier.width(94.dp),
                    text = pair.first,
                    style = MaterialTheme.typography.labelSmall,
                    color = textGray
                )
                if (pair.second is BookingStatus) {
                    Text(
                        modifier = Modifier
                            .padding(start = 25.dp)
                            .fillMaxWidth(),
                        text = BookingStatus.create((pair.second as BookingStatus).value),
                        style = MaterialTheme.typography.labelSmall,
                        color = statusColor,
                    )
                } else {
                    Text(
                        modifier = Modifier
                            .padding(start = 25.dp)
                            .fillMaxWidth(),
                        text = pair.second.toString(),
                        style = MaterialTheme.typography.labelSmall,
                        color = mainBlack
                    )
                }
            }
        }
    }
}