package fit.budle.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fit.budle.R
import fit.budle.model.tag.active.ActiveTagType
import fit.budle.model.tag.active.days
import fit.budle.model.tag.active.time
import fit.budle.ui.details.*
import fit.budle.ui.theme.*

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun OrderScreen(
    navHostController: NavHostController,
    establishmentId: String?,
    name: String?,
    getOrder: (Long, Long) -> String,
    sendGuestCount: (Int) -> Unit,
    sendData: (String) -> Unit,
    sendTime: (String) -> Unit
) {
    val userAmount = remember { mutableStateOf(1) }
    val userId: Long = 1

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (name != null) {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.bodyMedium,
                        color = textGray
                    )
                }
                IconButton(
                    modifier = Modifier.size(26.dp),
                    iconDescription = "Close",
                    iconId = R.drawable.x,
                    buttonColor = lightBlue,
                    crossColor = mainBlack,
                    elevation = 0.dp,
                    onClick = {
                        navHostController.popBackStack()
                    }
                )
            }
            BookingAmount(amount = userAmount, sendGuestCount)
            BookingDay(sendData)
            BookingTime(sendTime)
            BookingPreferences()
            BudleButton(
                topPadding = 40.dp,
                buttonText = "Выбрать место на карте",
                disabledButtonColor = lightBlue,
                activeButtonColor = lightBlue,
                disabledTextColor = mainBlack,
                activeTextColor = mainBlack,
                iconId = R.drawable.map,
                horizontalPadding = 0.dp,
                onClick = {
                    navHostController.navigate("home")
                }
            )
            BudleButton(
                topPadding = 15.dp,
                buttonText = "Забронировать",
                disabledButtonColor = fillPurple,
                activeButtonColor = fillPurple,
                disabledTextColor = mainWhite,
                activeTextColor = mainWhite,
                horizontalPadding = 0.dp,
                onClick = {
                    if (establishmentId != null) {
                        getOrder(establishmentId.toLong(), userId)
                    }
                    navHostController.navigate("home")
                }
            )
        }
    }
}

@Composable
fun BookingAmount(
    amount: MutableState<Int>,
    sendGuestCount: (Int) -> Unit
) {
    val leftCondition = amount.value > 1
    val rightCondition = amount.value < 10

    BlockWithHeader(headerText = "Количество гостей") {
        Row(
            modifier = Modifier
                .padding(top = 10.dp)
                .width(IntrinsicSize.Max)
                .height(IntrinsicSize.Max),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                iconDescription = "Minus",
                iconId = R.drawable.minus,
                modifier = Modifier.size(45.dp),
                buttonColor = lightBlue,
                crossColor = if (leftCondition) fillPurple else textGray,
                elevation = 0.dp,
                onClick = {
                    if (leftCondition) {
                        amount.value--
                        sendGuestCount(amount.value)
                    }
                }
            )
            Text(
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .width(40.dp),
                text = amount.value.toString(),
                style = MaterialTheme.typography.titleMedium,
                color = mainBlack
            )
            IconButton(
                iconDescription = "Plus",
                iconId = R.drawable.plus,
                modifier = Modifier.size(45.dp),
                buttonColor = lightBlue,
                crossColor = if (rightCondition) fillPurple else textGray,
                elevation = 0.dp,
                onClick = {
                    if (rightCondition) {
                        amount.value++
                        sendGuestCount(amount.value)
                    }
                }
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun BookingDay(sendData: (String) -> Unit) {
    BlockWithHeader(headerText = "День") {
        Row(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val data = tagList(
                initialState = days[0].tagId,
                tagList = days,
                tagType = ActiveTagType.CIRCLE
            )
            sendData(data.tagId.toString())
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun BookingTime(sendTime: (String) -> Unit) {
    BlockWithHeader(headerText = "Время") {
        Row(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val time = tagList(
                initialState = time[0].tagId,
                tagList = time,
            )
            sendTime(time.tagName)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun BookingPreferences() {
    BlockWithHeader(headerText = "Предпочтения") {
        Row(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {/*
            InfoTagList(
                horizontalPadding = 20.dp,
                tags = bookingTagList
            )*/
        }
    }
}
