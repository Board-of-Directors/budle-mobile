package fit.budle.request.response.customer

import fit.budle.dto.Exception
import fit.budle.dto.order.ScheduleDay

data class GetEstTimeResponse(val result: List<ScheduleDay>, val exception: Exception?)
