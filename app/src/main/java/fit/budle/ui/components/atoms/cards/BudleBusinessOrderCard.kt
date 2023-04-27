package fit.budle.ui.components.atoms.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fit.budle.R
import fit.budle.dto.order.Booking
import fit.budle.dto.order.BookingStatus
import fit.budle.ui.components.atoms.BudleButton
import fit.budle.ui.theme.*

@Composable
fun BudleBusinessOrderCard(
    modifier: Modifier = Modifier,
    booking: Booking,
) {

    var isExpanded by remember { mutableStateOf(false) }
    val bookingStatus = BookingStatus.create(booking.status)
    val dropDownIcon = if (!isExpanded) R.drawable.chevron_down else R.drawable.chevron_up

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
                headerSize = MaterialTheme.typography.bodyMedium
            )
            BudleCardRow(
                topPadding = 20.dp,
                header = bookingStatus.message,
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
                    BudleCardRow(
                        header = "Время",
                        headerSize = MaterialTheme.typography.labelSmall,
                        description = booking.startTime,
                        topPadding = 10.dp
                    )
                    BudleCardRow(
                        header = "Кол-во гостей",
                        headerSize = MaterialTheme.typography.labelSmall,
                        description = booking.guestCount.toString(),
                        topPadding = 20.dp
                    )
                }
            }
            BudleButton(
                onClick = {
                    if (bookingStatus == BookingStatus.REJECT) {
                        // TODO
                    }
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