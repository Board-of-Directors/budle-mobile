package fit.budle.dto.result

import fit.budle.dto.Exception
import fit.budle.dto.establishment.EstablishmentDtoResponseDto
import fit.budle.dto.order.Booking
import fit.budle.dto.tag.standard.TagResponse

sealed class OrderListResult {
    data class Success(val result: Array<Booking>, val exceptionMessage: String?) :
        OrderListResult()
    data class Failure(val throwable: Throwable) : OrderListResult()
}
