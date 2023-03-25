package fit.budle.screens.customer.establishments

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
import androidx.core.os.bundleOf
import androidx.navigation.NavHostController
import fit.budle.R
import fit.budle.components.atoms.BudleButton
import fit.budle.components.atoms.BudleIconButton
import fit.budle.components.data.TagType
import fit.budle.components.moleculas.BudleBlockWithHeader
import fit.budle.components.moleculas.budleTagList
import fit.budle.models.*
import fit.budle.navigation.navigate
import fit.budle.navigation.routes.NavRoute
import fit.budle.ui.theme.*

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun BookingProcessScreen(
    navHostController: NavHostController,
    establishmentCard: EstablishmentCard
) {

    val userAmount = remember { mutableStateOf(1) }
    val description = establishmentCard.establishmentCardModel.establishmentDescription

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
                Text(
                    text = description.name,
                    style = MaterialTheme.typography.bodyMedium,
                    color = textGray
                )
                BudleIconButton(
                    modifier = Modifier.size(26.dp),
                    iconDescription = "Close",
                    iconId = R.drawable.x,
                    buttonColor = lightBlue,
                    crossColor = mainBlack,
                    elevation = 0.dp,
                    onClick = {
                        navHostController.navigate(
                            NavRoute.EstablishmentCard.route,
                            bundleOf("EST_KEY" to establishmentCard)
                        )
                    }
                )
            }
            BookingAmount(amount = userAmount)
            BookingDay()
            BookingTime()
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
                    navHostController.navigate(NavRoute.MainPage.route)
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
                    navHostController.navigate(NavRoute.MainPage.route)
                }
            )
        }
    }
}

@Composable
fun BookingAmount(
    amount: MutableState<Int>
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
                    }
                }
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun BookingDay() {
    BudleBlockWithHeader(headerText = "День") {
        Row(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            budleTagList(
                initialState = days[0].tagId,
                tagList = days,
                tagType = TagType.CIRCLE
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun BookingTime() {
    BudleBlockWithHeader(headerText = "Время") {
        Row(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            budleTagList(
                initialState = time[0].tagId,
                tagList = time,
            )
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
        ) {
            budleTagList(
                tagList = bookingTagList,
                textColor = textGray
            )
        }
    }
}





















