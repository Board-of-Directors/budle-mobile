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
import fit.budle.dto.tag.active.ActiveTagType
import fit.budle.dto.tag.active.Tag
import fit.budle.dto.tag.active.days
import fit.budle.dto.tag.active.time
import fit.budle.ui.components.*
import fit.budle.ui.components.atoms.BudleButton
import fit.budle.ui.components.moleculas.BudleBlockWithHeader
import fit.budle.ui.components.moleculas.tag_list.BudleTagList
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

    /*
    val userAmount = remember { mutableStateOf(1) }
    val userId: Long = 1

    var selectedDay by remember{mutableState(Tag)}

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
                BudleIconButton(
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
    */
}

@Composable
fun BookingAmount(
    amount: MutableState<Int>,
    sendGuestCount: (Int) -> Unit
) {
    val leftCondition = amount.value > 1
    val rightCondition = amount.value < 10

    BudleBlockWithHeader(headerText = "Количество гостей") {
        Row(
            modifier = Modifier
                .padding(top = 10.dp)
                .width(IntrinsicSize.Max)
                .height(IntrinsicSize.Max),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BudleIconButton(
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
            BudleIconButton(
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
fun BookingDay(
    sendData: (String) -> Unit,
    selectedDay: Tag,
    onValueChange: (Tag) -> Unit
) {
    BudleBlockWithHeader(headerText = "День") {
        Row(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BudleTagList(
                initialState = days[0],
                onValueChange = { onValueChange(it) },
                tagList = days,
                tagType = ActiveTagType.CIRCLE
            )
            sendData(selectedDay.toString())
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun BookingTime(sendTime: (String) -> Unit) {
    BudleBlockWithHeader(headerText = "Время") {
        Row(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BudleTagList(
                initialState = time[0],
                tagList = time,
                onValueChange = {}
            )
            // sendTime(time.tagName)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun BookingPreferences() {
    BudleBlockWithHeader(headerText = "Предпочтения") {
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
