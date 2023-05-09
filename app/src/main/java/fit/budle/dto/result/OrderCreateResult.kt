package fit.budle.dto.result

import fit.budle.dto.Exception
import fit.budle.dto.establishment.EstablishmentDtoResponseDto
import fit.budle.dto.order.Booking
import fit.budle.dto.order.ScheduleDay
import fit.budle.dto.tag.standard.TagResponse

sealed class OrderCreateResult {
    data class Success(val result: Array<ScheduleDay>, val exceptionMessage: String?) :
        OrderCreateResult()
    data class Failure(val throwable: Throwable) : OrderCreateResult()
}
