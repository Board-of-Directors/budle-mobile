package fit.budle.dto.response

import fit.budle.dto.order.Booking
import fit.budle.dto.Exception

sealed interface OrderListResponse {
    data class BookingArrayResponse(val result: Array<Booking>, val exception: Exception?) :
        OrderListResponse
}
