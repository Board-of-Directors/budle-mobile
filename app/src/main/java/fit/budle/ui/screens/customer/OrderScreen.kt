package fit.budle.ui.screens.customer

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fit.budle.R
import fit.budle.event.customer.OrderCreateEvent
import fit.budle.ui.components.BudleIconButton
import fit.budle.ui.components.atoms.BudleButton
import fit.budle.ui.components.moleculas.BudleBookingTimeBlock
import fit.budle.ui.components.moleculas.counter.BudleCounter
import fit.budle.ui.components.moleculas.day_list.customer.BudleBookingDaysBlock
import fit.budle.ui.theme.fillPurple
import fit.budle.ui.theme.lightBlue
import fit.budle.ui.theme.mainBlack
import fit.budle.ui.theme.mainWhite
import fit.budle.ui.theme.textGray
import fit.budle.viewmodel.customer.MainViewModel
import fit.budle.viewmodel.customer.OrderCreateViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun OrderScreen(
    navHostController: NavHostController,
    mainViewModel: MainViewModel,
    orderViewModel: OrderCreateViewModel,
) {

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

            BudleCounter(
                initialState = orderViewModel.selectedSeatAmount,
                onValueChange = {
                    orderViewModel.selectedSeatAmount = it
                    orderViewModel.onEvent(OrderCreateEvent.SetSeatAmount)
                }
            )

            if (orderViewModel.estBookingDays.isNotEmpty()) {
                BudleBookingDaysBlock(
                    initialListState = orderViewModel.estBookingDays,
                    onValueChange = {
                        orderViewModel.selectedDay = it.tagId.toString()
                        orderViewModel.onEvent(
                            OrderCreateEvent.GetEstablishmentTime(
                                mainViewModel.establishmentCard.id
                            )
                        )
                        orderViewModel.onEvent(
                            OrderCreateEvent.SetDay
                        )
                    }
                )
            }

            if (orderViewModel.estBookingTime.isNotEmpty()) {
                BudleBookingTimeBlock(
                    initialListState = orderViewModel.estBookingTime,
                    onValueChange = {
                        orderViewModel.selectedTime = it.tagName
                        orderViewModel.onEvent(
                            OrderCreateEvent.SetTime
                        )
                    }
                )
            }

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
