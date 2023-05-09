package fit.budle.dto.response

import fit.budle.dto.Exception
import fit.budle.dto.order.ScheduleDay

sealed interface OrderCreateResponse {
    data class ScheduleDayArrayResponse(val result: Array<ScheduleDay>, val exception: Exception?) :
        OrderCreateResponse
}
