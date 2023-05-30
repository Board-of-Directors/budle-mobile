package fit.budle.ui.components.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import fit.budle.dto.enums.SwipeDirection
import fit.budle.event.customer.OrderCreateEvent
import fit.budle.ui.components.moleculas.BudleBookingTimeBlock
import fit.budle.ui.components.moleculas.day_list.customer.BudleBookingDaysBlock
import fit.budle.ui.theme.fillPurple
import fit.budle.ui.theme.mainBlack
import fit.budle.ui.theme.mainWhite
import fit.budle.ui.util.xml_parser.XMLShape
import fit.budle.viewmodel.customer.MainViewModel
import fit.budle.viewmodel.customer.OrderCreateViewModel
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BudleSlidebar(
    xmlShape: XMLShape,
    onClick: () -> Unit,
    orderViewModel: OrderCreateViewModel,
    mainViewModel: MainViewModel,
) {

    val swipeableState = rememberSwipeableState(SwipeDirection.Initial)

    Column(
        modifier = Modifier
            .offset(y = 20.dp)
            .fillMaxWidth()
            .zIndex(10f)
            .offset { IntOffset(0, swipeableState.offset.value.roundToInt()) },
        verticalArrangement = Arrangement.Bottom
    ) {
        Box(
            modifier = Modifier
                .size(100.dp, 10.dp)
                .align(Alignment.CenterHorizontally)
                .background(color = Color.Gray, shape = RoundedCornerShape(30.dp))
        )
        Card(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
                .weight(1f, false)
                .swipeable(
                    state = swipeableState,
                    anchors = mapOf(
                        0f to SwipeDirection.Initial,
                        700f to SwipeDirection.Down,
                    ),
                    thresholds = { _, _ -> FractionalThreshold(0.3f) },
                    orientation = Orientation.Vertical
                ),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(10.dp),
            colors = CardDefaults.cardColors(mainWhite)
        )
        {
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 20.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Столик №${xmlShape.id}",
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.bodyMedium,
                        color = mainBlack
                    )
                    Text(
                        text = "Свободно",
                        textAlign = TextAlign.End,
                        style = MaterialTheme.typography.bodyMedium,
                        color = mainBlack
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Количество мест",
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.bodyMedium,
                        color = mainBlack
                    )
                    Text(
                        text = "5 гостей",
                        textAlign = TextAlign.End,
                        style = MaterialTheme.typography.bodyMedium,
                        color = mainBlack
                    )
                }

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

                BudleButton(
                    bottomPadding = 20.dp,
                    topPadding = 20.dp,
                    horizontalPadding = 0.dp,
                    onClick = onClick,
                    buttonText = "Забронировать",
                    disabledButtonColor = fillPurple,
                    disabledTextColor = mainWhite
                )
            }
        }
    }
}