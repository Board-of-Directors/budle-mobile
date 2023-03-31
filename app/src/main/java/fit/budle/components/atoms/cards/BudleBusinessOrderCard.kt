package fit.budle.components.atoms.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fit.budle.R
import fit.budle.components.atoms.BudleButton
import fit.budle.models.Booking
import fit.budle.models.BookingStatus
import fit.budle.ui.theme.*

@Composable
fun BudleBusinessOrderCard(
        modifier: Modifier = Modifier,
        booking: Booking
) {

    var isExpanded by remember { mutableStateOf(false) }
    val bookingStatus = booking.infoList[0].second as BookingStatus
    val dropDownIcon = if (!isExpanded) R.drawable.chevron_down else R.drawable.chevron_up
    val descrMessage = if (booking.tableNumber != null) "Столик №${booking.tableNumber}" else ""

    val descriptionColor = when (bookingStatus) {
        BookingStatus.REJECT -> backgroundError
        BookingStatus.WAIT -> textGray
        BookingStatus.CONFIRM -> backgroundSuccess
    }
    val buttonText = when (bookingStatus) {
        BookingStatus.REJECT -> "Восстановить заказ"
        BookingStatus.WAIT -> "Принять заказ"
        BookingStatus.CONFIRM -> "Удалить заказ"
    }

    Card(
            modifier = modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
            border = BorderStroke(2.dp, lightBlue),
            colors = CardDefaults.cardColors(mainWhite),
            shape = RoundedCornerShape(15.dp)
    ) {
        Column(
                modifier = Modifier.padding(20.dp)
        ) {
            BudleCardRow(
                    topPadding = 0.dp,
                    header = "Заказ №${booking.id}",
                    headerSize = MaterialTheme.typography.bodyMedium,
                    description = descrMessage,
            )
            BudleCardRow(
                    topPadding = 20.dp,
                    header = booking.infoList[0].first,
                    headerSize = MaterialTheme.typography.labelSmall,
                    description = bookingStatus.message,
                    descriptionColor = descriptionColor
            )
            BudleCardRow(
                    iconId = dropDownIcon,
                    topPadding = 7.dp,
                    header = "Детали заказа",
                    headerSize = MaterialTheme.typography.labelSmall,
                    iconButton = true,
                    onClickIconButton = {
                        isExpanded = !isExpanded
                    },
                    description = ""
            )
            if (isExpanded) {
                Column(
                        modifier = Modifier.fillMaxWidth()
                ) {
                    for (i in 1 until booking.infoList.size) {
                        BudleCardRow(
                                header = booking.infoList[i].first,
                                headerSize = MaterialTheme.typography.labelSmall,
                                description = booking.infoList[i].second.toString(),
                                topPadding = if (i == 1) 10.dp else 20.dp
                        )
                    }
                }
            }
            BudleButton(
                    onClick = {
                        if (bookingStatus == BookingStatus.REJECT) {
                            booking.isRejected.value = false
                        } else
                    },
                    topPadding = if (!isExpanded) 10.dp else 15.dp,
                    horizontalPadding = 0.dp,
                    buttonText = buttonText,
                    disabledButtonColor = lightBlue,
                    disabledTextColor = if (bookingStatus == BookingStatus.CONFIRM) backgroundError else fillPurple
            )
        }
    }
}