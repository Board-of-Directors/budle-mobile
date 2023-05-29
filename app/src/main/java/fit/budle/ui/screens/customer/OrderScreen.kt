package fit.budle.ui.screens.customer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import fit.budle.event.customer.MainEvent
import fit.budle.event.customer.OrderCreateEvent
import fit.budle.ui.components.BudleIconButton
import fit.budle.ui.components.atoms.BudleButton
import fit.budle.ui.components.moleculas.BudleBlockWithHeader
import fit.budle.ui.components.moleculas.tag_list.BudleTagList
import fit.budle.ui.theme.fillPurple
import fit.budle.ui.theme.lightBlue
import fit.budle.ui.theme.mainBlack
import fit.budle.ui.theme.mainWhite
import fit.budle.ui.theme.textGray
import fit.budle.viewmodel.customer.MainViewModel
import fit.budle.viewmodel.customer.OrderCreateViewModel

@Composable
fun OrderScreen(
    navHostController: NavHostController,
    mainViewModel: MainViewModel,
    orderViewModel: OrderCreateViewModel,
) {

    mainViewModel.onEvent(MainEvent.GetEstablishment)
    val userAmount = remember { mutableStateOf(1) }

    Surface(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            orderViewModel.onEvent(
                OrderCreateEvent.GetEstablishmentTime(
                    mainViewModel.establishmentCard.id
                )
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = mainViewModel.establishmentCard.name,
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
                        navHostController.popBackStack()
                    }
                )
            }

            BookingAmount(userAmount, orderViewModel)
            BookingDay(orderViewModel, mainViewModel.establishmentCard.id.toString())
            BookingTime(orderViewModel)
            BookingPreferences()
            if (mainViewModel.establishmentCard.hasMap) {
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
                        navHostController.navigate("orderCreation/map")
                    }
                )
            }
            BudleButton(
                topPadding = 15.dp,
                buttonText = "Забронировать",
                disabledButtonColor = fillPurple,
                activeButtonColor = fillPurple,
                disabledTextColor = mainWhite,
                activeTextColor = mainWhite,
                horizontalPadding = 0.dp,
                onClick = {
                    orderViewModel.onEvent(
                        OrderCreateEvent.PostOrder(
                            mainViewModel.establishmentCard.id
                        )
                    )
                    navHostController.navigate("main")
                }
            )
        }
    }
}

@Composable
fun BookingAmount(
    amount: MutableState<Int>,
    viewModel: OrderCreateViewModel,
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
            viewModel.selectedSeatAmount = amount.value
            viewModel.onEvent(OrderCreateEvent.SetSeatAmount)
        }
    }
}

@Composable
fun BookingDay(
    viewModel: OrderCreateViewModel,
    establishmentId: String?,
) {
    BudleBlockWithHeader(headerText = "День") {
        Row(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (viewModel.estBookingDays.isNotEmpty()) {
                BudleTagList(
                    initialState = viewModel.estBookingDays[0],
                    onValueChange = {
                        viewModel.selectedDay = it.tagId.toString()
                        viewModel.onEvent(
                            OrderCreateEvent.GetEstablishmentTime(
                                establishmentId!!.toLong()
                            )
                        )
                    },
                    tagList = viewModel.estBookingDays,
                    tagType = ActiveTagType.CIRCLE
                )
                viewModel.onEvent(OrderCreateEvent.SetDay)
            }
        }
    }
}

@Composable
fun BookingTime(viewModel: OrderCreateViewModel) {
    BudleBlockWithHeader(headerText = "Время") {
        Row(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (viewModel.estBookingTime.isNotEmpty()) {
                BudleTagList(
                    initialState = viewModel.estBookingTime[0],
                    tagList = viewModel.estBookingTime,
                    onValueChange = { viewModel.selectedTime = it.tagName }
                )
                viewModel.onEvent(OrderCreateEvent.SetTime)
            }
        }
    }
}

@Composable
fun BookingPreferences() {
    BudleBlockWithHeader(headerText = "Предпочтения") {
        Row(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            /*
            InfoTagList(
                horizontalPadding = 20.dp,
                tags = bookingTagList
            )*/
        }
    }
}
