package fit.budle.request.response.customer

import fit.budle.dto.Exception
import fit.budle.dto.order.Booking

sealed interface OrderListResponse {
    data class BookingArrayResponse(val result: Array<Booking>, val exception: Exception?) :
        OrderListResponse
}
