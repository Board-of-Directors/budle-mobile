package fit.budle.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fit.budle.dto.events.OrderCreateEvent
import fit.budle.dto.order.ScheduleDay
import fit.budle.dto.result.DefaultResult
import fit.budle.dto.result.OrderCreateResult
import fit.budle.dto.tag.active.ActiveCircleTag
import fit.budle.dto.tag.active.RectangleActiveTag
import fit.budle.repository.OrderCreateRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderCreateViewModel @Inject constructor(
    private var repository: OrderCreateRepository,
) : ViewModel() {
    var result: String by mutableStateOf("")
    var dateVar: String by mutableStateOf("")
    var timeVar: String by mutableStateOf("")
    private var seatVar: String by mutableStateOf("1")
    var guestCountVar: Int by mutableStateOf(1)
    private var scheduleArray: Array<ScheduleDay> by mutableStateOf(emptyArray())

    var dayArray: List<ActiveCircleTag> by mutableStateOf(emptyList())

    var timeArray: List<RectangleActiveTag> by mutableStateOf(emptyList())
    var currentTime: RectangleActiveTag? by mutableStateOf(null)

    val userId: Long = 1

    fun onEvent(event: OrderCreateEvent) {
        when (event) {
            is OrderCreateEvent.postOrder -> {
                viewModelScope.launch {
                    Log.e("ESTID", event.establishmentId.toString())
                    Log.e("UID", userId.toString())
                    Log.e("GUEST", guestCountVar.toString())
                    Log.e("TIME", "$timeVar:00")
                    Log.e("DATE", "2023-05-$dateVar")
                    Log.e("SEAT", seatVar)
                    if (dateVar.isNotEmpty() && timeVar.isNotEmpty()) {
                        when (val response = repository.postOrder(
                            event.establishmentId,
                            userId,
                            guestCountVar,
                            "$timeVar:00",
                            "2023-05-$dateVar",
                            null
                        )) {
                            is DefaultResult.Success -> {
                                Log.d("BOOKVIEWMODEL", "SUCCESS")
                                result =
                                    if (response.result == null) "NULL" else if (response.result == true) "TRUE" else "FALSE"
                                Log.d("BOOKVIEWMODEL", response.exceptionMessage.toString())
                            }
                            is DefaultResult.Failure -> {
                                Log.e("BOOKVIEWMODEL", "FAILURE")
                                response.throwable.message?.let { Log.e("BOOKVIEWMODEL", it) }
                            }
                        }
                    }
                }
            }
            is OrderCreateEvent.getEstablishmentTime -> {
                viewModelScope.launch {
                    when (val response = repository.getEstablishmentTime(
                        event.establishmentId,
                    )) {
                        is OrderCreateResult.Success -> {
                            Log.d("BOOKVIEWMODEL", "SUCCESS")
                            scheduleArray = response.result
                            if (scheduleArray.isEmpty()) {
                                Log.e("STATUS", "EMPTY")
                                Log.e("ID", event.establishmentId.toString())
                            } else {
                                Log.e("STATUS", "NOT EMPTY")
                            }
                            dayArray = scheduleToDays(response.result)
                            timeArray = getTime()
                            if (timeArray.isNotEmpty()) {
                                currentTime = timeArray[0]
                            }
                        }
                        is OrderCreateResult.Failure -> {
                            Log.e("BOOKVIEWMODEL", "FAILURE")
                            response.throwable.message?.let { Log.e("BOOKVIEWMODEL", it) }
                        }
                    }
                }
            }
        }
    }

    private fun getTime(): List<RectangleActiveTag> { //TODO Нужно нормально сделать
        val result: MutableList<RectangleActiveTag> = mutableListOf()
        var count = 0
        if (dateVar.isNotEmpty()) {
            for (i in scheduleArray) {
                if (i.dayNumber == dateVar) {
                    for (j in i.times) {
                        result.add(RectangleActiveTag(j, count))
                        count++
                    }
                }
            }
        }
        if (result.isEmpty()) {
            Log.e("RESULT", "EMPTY")
        } else {
            Log.e("RESULT", "NOT EMPTY")
        }
        return result
    }
}

fun scheduleToDays(scheduleArray: Array<ScheduleDay>): MutableList<ActiveCircleTag> {
    val result: MutableList<ActiveCircleTag> = mutableListOf()
    for (i in scheduleArray) {
        result.add(ActiveCircleTag(i.dayNumber.trim().toInt(), i.dayName))
    }
    return result
}
