package fit.budle.dto.response

import fit.budle.dto.order.Booking
import fit.budle.dto.Exception

sealed interface EstOrderResponse{
    data class GetEstOrderListResponse(val result: List<Booking>, val exception: Exception?) : EstOrderResponse
    data class PutEstOrderResponse(val result: Booking, val exception: Exception?): EstOrderResponse
    data class DeleteEstOrderResponse(val result: Booking, val exception: Exception?) : EstOrderResponse
}
