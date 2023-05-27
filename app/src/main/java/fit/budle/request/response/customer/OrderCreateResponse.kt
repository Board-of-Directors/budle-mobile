package fit.budle.request.response.customer

import fit.budle.dto.Exception
import fit.budle.dto.order.ScheduleDay

sealed interface OrderCreateResponse {
    data class ScheduleDayArrayResponse(val result: Array<ScheduleDay>, val exception: Exception?) :
        OrderCreateResponse

    data class GetEstabmishmentMapResponse(val result: String?, val exception: Exception?) :
        OrderCreateResponse
}
