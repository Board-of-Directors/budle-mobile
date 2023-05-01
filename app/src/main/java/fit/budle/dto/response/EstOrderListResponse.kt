package fit.budle.dto.response

import fit.budle.dto.order.Booking
import fit.budle.dto.Exception
import fit.budle.dto.order.BusinessOrderDto

sealed interface EstOrderResponse{
    data class GetEstOrderListResponse(val result: List<BusinessOrderDto>, val exception: Exception?) : EstOrderResponse
    data class AcceptEstOrderResponse(val result: BusinessOrderDto?, val exception: Exception?): EstOrderResponse
    data class RejectEstOrderResponse(val result: BusinessOrderDto?, val exception: Exception?) : EstOrderResponse
}
