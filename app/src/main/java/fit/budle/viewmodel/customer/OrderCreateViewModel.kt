package fit.budle.viewmodel.customer

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fit.budle.dto.order.RequestOrderDto
import fit.budle.dto.order.ScheduleDay
import fit.budle.dto.tag.active.ActiveCircleTag
import fit.budle.dto.tag.active.RectangleActiveTag
import fit.budle.event.customer.OrderCreateEvent
import fit.budle.repository.customer.OrderCreateRepository
import fit.budle.request.result.customer.OrderCreateResult
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class OrderCreateViewModel @Inject constructor(
    private var orderCreateRepository: OrderCreateRepository,
) : ViewModel() {

    // initial state of the request DTO
    val requestOrderDto by mutableStateOf(RequestOrderDto())

    // available booking days and time of the establishment
    var estBookingDays by mutableStateOf(emptyList<ActiveCircleTag>())
    var estBookingTime by mutableStateOf(emptyList<RectangleActiveTag>())

    // selected day, time and seat amount by user
    var selectedSeatId by mutableStateOf(-1)
    var selectedSeatAmount by mutableStateOf(1)
    var selectedDay: String by mutableStateOf("")
    var selectedTime: String by mutableStateOf("")

    // schedule of the current establishment, which is got from server
    var establishmentSchedule = listOf<ScheduleDay>()

    @RequiresApi(Build.VERSION_CODES.O)
    fun onEvent(event: OrderCreateEvent) {
        when (event) {
            is OrderCreateEvent.PostOrder -> {
                viewModelScope.launch {
                    LogOrder(event.establishmentId)
                    requestOrderDto.establishmentId = event.establishmentId.toInt()
                    orderCreateRepository.postOrder(requestOrderDto)
                }
            }

            is OrderCreateEvent.GetEstablishmentTime -> {
                viewModelScope.launch {
                    when (val response =
                        orderCreateRepository.getEstablishmentTime(event.establishmentId)
                    ) {
                        is OrderCreateResult.Success -> {
                            establishmentSchedule = response.result
                            Log.i("SIZE", establishmentSchedule.size.toString())
                            estBookingDays = convertScheduleToDays()
                            estBookingTime = createValidTimeList()
                        }

                        is OrderCreateResult.Failure -> {}
                    }
                }
            }

            is OrderCreateEvent.SetSeatAmount -> {
                requestOrderDto.guestCount = selectedSeatAmount
            }

            is OrderCreateEvent.SetDay -> {
                val now = LocalDate.now()
                val year = now.year
                val month = if (now.dayOfMonth >= selectedDay.toInt())
                    now.monthValue else LocalDate.now().monthValue + 1
                val monthString = if (month.toString().length == 1) "0$month" else "$month";
                if (selectedDay.length == 1) {
                    requestOrderDto.date = "$year-$monthString-0$selectedDay"
                } else {
                    requestOrderDto.date = "$year-$monthString-$selectedDay"
                }

            }

            is OrderCreateEvent.SetTime -> {
                requestOrderDto.time = "$selectedTime:00"
            }

            is OrderCreateEvent.SetSpot -> {
                requestOrderDto.spotId = selectedSeatId
            }
        }
    }

    /**
     * Creates list of the time tag by selected day tag
     */
    private fun createValidTimeList(): List<RectangleActiveTag> {
        val timeTagList = mutableListOf<RectangleActiveTag>()
        establishmentSchedule.forEach { scheduleDay ->
            if (scheduleDay.dayNumber == selectedDay) {
                scheduleDay.times.forEachIndexed { timeId, time ->
                    timeTagList.add(
                        RectangleActiveTag(
                            tagId = timeId,
                            tagName = time
                        )
                    )
                }
            }
        }
        return timeTagList
    }

    /**
     * Converts schedule to the list of tags, which element contain
     * information about day name, e.g. "Mon", "Thu" and number of the day
     * in current month.
     */
    private fun convertScheduleToDays(): List<ActiveCircleTag> {
        val dayTagList = mutableListOf<ActiveCircleTag>()
        establishmentSchedule.forEach { scheduleDay ->
            dayTagList.add(
                ActiveCircleTag(
                    scheduleDay.dayNumber.trim().toInt(),
                    scheduleDay.dayName
                )
            )
        }
        return dayTagList
    }

    private fun LogOrder(establishmentId: Long) {
        Log.e("ESTID", establishmentId.toString())
        Log.e("GUEST", selectedSeatAmount.toString())
        Log.e("TIME", selectedTime)
        Log.e("DATE", "2023-05-$selectedDay")
    }
}
